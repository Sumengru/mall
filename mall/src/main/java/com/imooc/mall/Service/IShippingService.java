package com.imooc.mall.Service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.pojo.Shipping;
import com.imooc.mall.vo.ResponseVo;

import java.util.Map;

public interface IShippingService {
    ResponseVo<Map<String,Integer>> add(Integer uid, ShippingForm form);

    ResponseVo delete(Integer uid, Integer shipingId);

    ResponseVo update(Integer uid, Integer shippingId, ShippingForm form);

    ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize, Integer uid);
}
