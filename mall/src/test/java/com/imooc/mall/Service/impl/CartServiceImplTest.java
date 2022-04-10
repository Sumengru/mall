package com.imooc.mall.Service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.ICartService;
import com.imooc.mall.form.CartAddForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CartServiceImplTest extends MallApplicationTests {
    @Autowired
    private ICartService cartService;
    @Test
    public void add() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(28);
        cartAddForm.setSeleted(true);
        cartService.add(1,cartAddForm);
    }
}