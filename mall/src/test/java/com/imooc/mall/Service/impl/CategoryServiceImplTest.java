package com.imooc.mall.Service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.ICategoryService;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceImplTest extends MallApplicationTests {
    @Autowired
    private ICategoryService categoryService;

    @Test
    public void selectAll() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectAll();
        assert (ResponseEnums.SUCCESS.getCode() == listResponseVo.getStatus());
    }
}