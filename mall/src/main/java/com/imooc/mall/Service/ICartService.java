package com.imooc.mall.Service;

import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ProductVo;
import com.imooc.mall.vo.ResponseVo;

public interface ICartService {
    public ResponseVo<CartVo> add(Integer uid, CartAddForm cartAddForm);
}
