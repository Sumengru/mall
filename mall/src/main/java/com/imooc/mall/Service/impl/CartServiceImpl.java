package com.imooc.mall.Service.impl;

import com.google.gson.Gson;
import com.imooc.mall.Service.ICartService;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.pojo.Cart;
import com.imooc.mall.pojo.Product;
import com.imooc.mall.vo.CartProductVO;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.imooc.mall.enums.ProductStatusEnums.ON_SALE;
import static com.imooc.mall.enums.ResponseEnums.*;

@Service
public class CartServiceImpl implements ICartService {
    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();
    @Override
    public ResponseVo<CartVo> add(Integer uid,CartAddForm cartAddForm) {
        Integer quantity = 1;
        //判断商品是否存在；
        // 库存是否在售；
        // 商品是否充足；

        Product product = productMapper.selectByPrimaryKey(cartAddForm.getProductId());
        if(product == null){
            return ResponseVo.error(PRODUCT_NOT_EXIST);
        }
        if(!product.getStatus().equals(ON_SALE.getCode())){
            return ResponseVo.error(PREDUCT_OFF_SALE_OR_DELETE);
        }
        //key
//        redisTemplate.opsForValue().set(String.format(CART_REDIS_KEY_TEMPLATE,uid),gson.toJson(new Cart(product.getId(),quantity,cartAddForm.getSeleted())));
//
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        Cart cart;
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String vaule = opsForHash.get(redisKey, String.valueOf(product.getId()));
        if(StringUtils.isEmpty(vaule)){
            //redis没有该商品
            cart = new Cart(product.getId(),quantity,cartAddForm.getSeleted());
        }
        else{
            //已经有了数量+1
            cart = gson.fromJson(vaule,Cart.class);
            cart.setQuantity(quantity + 1);
        }

        opsForHash.put(redisKey, String.valueOf(product.getId()),gson.toJson(cart));

        return null;
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        Map<String, String> entries = opsForHash.entries(redisKey);
        CartVo cartVo = new CartVo();
        Boolean selectAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        List<CartProductVO> cartProductVOList =new ArrayList<>();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            Product product = productMapper.selectByPrimaryKey(productId);
            if(product != null){
                CartProductVO cartProductVO = new CartProductVO(
                        productId,
                        cart.getQuantity(),
                        product.getName(),
                        product.getSubtitle(),
                        product.getMainImage(),
                        product.getPrice(),
                        product.getStatus(),
                        product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        cart.getProductSeleted()
                        );
                cartTotalQuantity = cartTotalQuantity + cart.getQuantity();
                cartProductVOList.add(cartProductVO);
                if(!cart.getProductSeleted()){
                    selectAll = false;
                }
                if(cart.getProductSeleted()){
                    cartTotalPrice = cartTotalPrice.add(cartProductVO.getProductTotalPrice());
                }
            }
        }
        cartVo.setSeletedAll(selectAll);
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setProductVOList(cartProductVOList);
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productID, CartUpdateForm form) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        String value = opsForHash.get(redisKey, String.valueOf(productID));
        if(StringUtils.isEmpty(value)){
            return ResponseVo.error(CART_PRODUCT_NOT_EXIST);
        }
        Cart cart = gson.fromJson(value,Cart.class);
            if(form.getSelected() != null){
                cart.setProductSeleted(form.getSelected());
            }
            if(form.getQuantity() != null && form.getQuantity() >= 0){
                cart.setQuantity(form.getQuantity());
            }
            opsForHash.put(redisKey,String.valueOf(productID),gson.toJson(cart));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productID) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        opsForHash.delete(redisKey,String.valueOf(productID));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        for (Cart cart : listForCart(uid)) {
            cart.setProductSeleted(true);
            opsForHash.put(redisKey,String.valueOf(cart.getProductId()),gson.toJson(cart));
        }

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        for (Cart cart : listForCart(uid)) {
            cart.setProductSeleted(false);
            opsForHash.put(redisKey,String.valueOf(cart.getProductId()),gson.toJson(cart));
        }

        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        Integer sum = 0;
        for (Cart cart : listForCart(uid)) {
            sum = sum + cart.getQuantity();
        }
        return ResponseVo.success(sum);
    }

    private List<Cart> listForCart(Integer uid){
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = opsForHash.entries(redisKey);
        ArrayList<Cart> carts = new ArrayList<>();
        entries.entrySet().forEach(
                i -> carts.add(gson.fromJson(i.getValue(),Cart.class))
        );
        return carts;
    }
}

















