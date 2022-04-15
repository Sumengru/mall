package com.imooc.mall.Service.impl;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.Service.IShippingService;
import com.imooc.mall.dao.ShippingMapper;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.pojo.Shipping;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    @Override
    public ResponseVo<Map<String,Integer>> add(Integer uid, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form,shipping);
        shipping.setUserId(uid);
        int raw = shippingMapper.insert(shipping);
        if(raw == 0){
            return ResponseVo.error(ResponseEnums.ERROR);
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("shippingId",raw);
        return ResponseVo.success(map);
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
