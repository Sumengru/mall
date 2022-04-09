package com.imooc.mall.Service.impl;

import com.imooc.mall.Service.IProductService;
import com.imooc.mall.dao.ProductMapper;
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
    public ResponseVo<List<ProductVo>> list(Integer categoryId, Integer pageNumber, Integer pagesize) {
        Set<Integer> categorySet = new HashSet<>();
        if (categoryId != null) {
            categoryService.findSubCategoryId(categoryId, categorySet);
            categorySet.add(categoryId);
        }
        List<ProductVo> productVoList = productMapper.selectByCategoryIdSet(categorySet).stream()
                .map(e ->
                {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());
        return ResponseVo.success(productVoList);
    }
}
