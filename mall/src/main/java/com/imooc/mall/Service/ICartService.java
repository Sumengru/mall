package com.imooc.mall.Service;

import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;

public interface ICartService {
     ResponseVo<CartVo> add(Integer uid, CartAddForm cartAddForm);
     ResponseVo<CartVo> list(Integer uid);
     ResponseVo<CartVo> update(Integer uid, Integer productID, CartUpdateForm form);
     ResponseVo<CartVo> delete(Integer uid, Integer productID);
     ResponseVo<CartVo> selectAll(Integer uid);
     ResponseVo<CartVo> unSelectAll(Integer uid);
     ResponseVo<Integer>sum(Integer uid);
}
