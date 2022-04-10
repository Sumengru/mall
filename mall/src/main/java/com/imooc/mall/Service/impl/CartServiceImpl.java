package com.imooc.mall.Service.impl;

import com.google.gson.Gson;
import com.imooc.mall.Service.ICartService;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.pojo.Cart;
import com.imooc.mall.pojo.Product;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.imooc.mall.enums.ProductStatusEnums.ON_SALE;
import static com.imooc.mall.enums.ResponseEnums.PREDUCT_OFF_SALE_OR_DELETE;
import static com.imooc.mall.enums.ResponseEnums.PRODUCT_NOT_EXIST;

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
}
