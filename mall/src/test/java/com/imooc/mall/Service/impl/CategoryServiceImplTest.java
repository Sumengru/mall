package com.imooc.mall.Service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.ICategoryService;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@Slf4j
public class CategoryServiceImplTest extends MallApplicationTests {
    @Autowired
    private ICategoryService categoryService;

    @Test
    public void selectAll() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectAll();
        assert (ResponseEnums.SUCCESS.getCode() == listResponseVo.getStatus());
    }

    @Test
    public void findSubCategoryId() {
        Set<Integer> set = new HashSet<>();
        categoryService.findSubCategoryId(100001,set);
        log.info("result is {}",set);
    }
}