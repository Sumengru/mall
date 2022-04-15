package com.imooc.mall.Service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.ICartService;
import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CartServiceImplTest extends MallApplicationTests {
    @Autowired
    private ICartService cartService;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Test
    public void add() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(26);
        cartAddForm.setSeleted(true);
        cartService.add(1,cartAddForm);
    }

    @Test
    public void list() {
        ResponseVo<CartVo> cartVoResponseVo = cartService.list(1);
        log.info("list = {}",gson.toJson(cartVoResponseVo));
    }

    @Test
    public void update() {
        CartUpdateForm form = new CartUpdateForm();
        form.setQuantity(10);
        form.setSelected(false);
        ResponseVo<CartVo> cartVoResponseVo = cartService.update(1,26,form);
        log.info("list = {}",gson.toJson(cartVoResponseVo));
    }

    @Test
    public void delete() {
        ResponseVo<CartVo> cartVoResponseVo = cartService.delete(1, 26);
        log.info("result is {}", gson.toJson(cartVoResponseVo));
    }

    @Test
    public void selectAll() {
        ResponseVo<CartVo> cartVoResponseVo = cartService.selectAll(1);
        log.info("result is {}", gson.toJson(cartVoResponseVo));
    }

    @Test
    public void unSelectAll() {
        ResponseVo<CartVo> cartVoResponseVo = cartService.unSelectAll(1);
        log.info("result is {}", gson.toJson(cartVoResponseVo));
    }

    @Test
    public void sum() {
        ResponseVo<Integer> vo = cartService.sum(1);
        log.info("result is {}", gson.toJson(vo));
    }
}