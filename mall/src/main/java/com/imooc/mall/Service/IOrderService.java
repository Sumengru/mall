package com.imooc.mall.Service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.vo.OrderVo;
import com.imooc.mall.vo.ResponseVo;

public interface IOrderService {
    ResponseVo<OrderVo> create(Integer uid, Integer shippingId);

    ResponseVo<OrderVo> cancel(Integer uid, Integer shippingId);

    ResponseVo<OrderVo> detail(Integer uid, Integer shippingId);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);
}

