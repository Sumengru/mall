package com.imooc.mall.Service.impl;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.Service.IOrderService;
import com.imooc.mall.vo.OrderVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {
    @Override
    public ResponseVo<OrderVo> create(Integer uid, Integer shippingId) {
        return null;
    }

    @Override
    public ResponseVo<OrderVo> cancel(Integer uid, Integer shippingId) {
        return null;
    }

    @Override
    public ResponseVo<OrderVo> detail(Integer uid, Integer shippingId) {
        return null;
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        return null;
    }
}
