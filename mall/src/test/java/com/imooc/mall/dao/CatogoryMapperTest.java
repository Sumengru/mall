package com.imooc.mall.dao;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.pojo.Catogory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CatogoryMapperTest extends MallApplicationTests {
    @Autowired
    private CatogoryMapper catogoryMapper;
    @Test
    public void findByID() {
        Catogory category = catogoryMapper.FindByID(100004);
        System.out.println(category);
    }

    @Test
    public void queryByID() {
        Catogory catogory = catogoryMapper.QueryByID(100004);
        System.out.println(catogory);
    }
}