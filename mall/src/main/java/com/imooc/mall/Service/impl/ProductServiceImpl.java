package com.imooc.mall.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.Service.IProductService;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.enums.ProductStatusEnums;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.pojo.Product;
import com.imooc.mall.vo.ProductDetailVo;
import com.imooc.mall.vo.ProductVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.imooc.mall.enums.ProductStatusEnums.DELETE;
import static com.imooc.mall.enums.ProductStatusEnums.OFF_SAFE;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    /*
     *1.指定ID，去类目表中查询parent_id 与 ID 与之匹配的 ID 存入set 中
     * 2.根据set中的ID 去产品表中查询与之匹配的产品。
     */
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categorySet = new HashSet<>();
        if (categoryId != null) {
            categoryService.findSubCategoryId(categoryId, categorySet);
            categorySet.add(categoryId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectByCategoryIdSet(categorySet);
        List<ProductVo> productVoList = productList.stream()
                .map(e ->
                {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer roductId) {
        Product product = productMapper.selectByPrimaryKey(roductId);
        if(product.getStatus().equals(OFF_SAFE.getCode()) || product.getStatus().equals(DELETE.getCode())){
            return ResponseVo.error(ResponseEnums.PREDUCT_OFF_SALE_OR_DELETE);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product,productDetailVo);
        return ResponseVo.success(productDetailVo);
    }
}
