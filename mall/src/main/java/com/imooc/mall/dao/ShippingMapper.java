package com.imooc.mall.dao;

import com.imooc.mall.pojo.Shipping;

import java.util.List;
import java.util.Map;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByUidAndShippingId(Integer uid, Integer id);

    List<Shipping> selectByUid(Integer uid);

}