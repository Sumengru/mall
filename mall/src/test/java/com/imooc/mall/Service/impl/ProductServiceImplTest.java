package com.imooc.mall.Service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.IProductService;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.vo.ProductVo;
import com.imooc.mall.vo.ResponseVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends MallApplicationTests {
    @Autowired
    private IProductService productService;
    @Test
    public void list() {
        ResponseVo<List<ProductVo>> list = productService.list(null, 1, 1);
        assert(list.getStatus() == ResponseEnums.SUCCESS.getCode());
    }
}