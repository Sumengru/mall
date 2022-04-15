package com.imooc.mall.Service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.IShippingService;
import com.imooc.mall.dao.ShippingMapper;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
public class ShippingServiceImplTest extends MallApplicationTests {
    @Autowired
    private IShippingService shippingService;
    private Integer uid = 1;
    @Test
    public void add() {
        ShippingForm form = new ShippingForm();
        form.setReceiverAddress("开心岛");
        form.setReceiverCity("延边市");
        form.setReceiverDistrict("不知道");
        form.setReceiverMobile("18888888888");
        form.setReceiverName("张三");
        form.setReceiverZip("222");
        form.setReceiverPhone("123456");
        form.setReceiverProvince("吉林省");
        ResponseVo<Map<String, Integer>> add = shippingService.add(uid, form);
        log.info("result = {}",add);
        assert(add.getStatus().equals(ResponseEnums.SUCCESS.getCode()));
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void list() {
    }
}