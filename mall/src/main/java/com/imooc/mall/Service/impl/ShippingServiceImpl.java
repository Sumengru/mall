package com.imooc.mall.Service.impl;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.Service.IShippingService;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.vo.ResponseVo;

public class ShippingServiceImpl implements IShippingService {
    @Override
    public ResponseVo<Integer> add(Integer uid, ShippingForm form) {
        return null;
    }

    @Override
    public ResponseVo delete(Integer uid, Integer shipingId) {
        return null;
    }

    @Override
    public ResponseVo update(Integer uid, Integer shippingId, ShippingForm form) {
        return null;
    }

    @Override
    public ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize, Integer uid) {
        return null;
    }
}
