package com.imooc.mall.Service.impl;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.IShippingService;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@Slf4j
public class ShippingServiceImplTest extends MallApplicationTests {
    @Autowired
    private IShippingService shippingService;
    private Integer uid = 1;
    private ShippingForm form;
    private Integer shippingId;

    @Before
    public void before() {
        ShippingForm form = new ShippingForm();
        form.setReceiverAddress("开心岛");
        form.setReceiverCity("延边市");
        form.setReceiverDistrict("不知道");
        form.setReceiverMobile("18888888888");
        form.setReceiverName("小郑下");
        form.setReceiverZip("222");
        form.setReceiverPhone("123456");
        form.setReceiverProvince("吉林省");
        this.form = form;
        add();
    }
    public void add() {
        ResponseVo<Map<String, Integer>> add = shippingService.add(uid, form);
        log.info("result = {}", add);
        this.shippingId = add.getData().get("shippingId");
        assert (add.getStatus().equals(ResponseEnums.SUCCESS.getCode()));
    }


    @Test
    public void update() {
        //this.shippingId = shippingId + 1;
        ResponseVo raw = shippingService.update(1, shippingId, form);
        log.info("result = {}", raw);
        assert (raw.getStatus().equals(ResponseEnums.SUCCESS.getCode()));
    }

    @Test
    public void list() {
        ResponseVo<PageInfo> list = shippingService.list(3, 1, 1);
        log.info("result = {}", list);
        assert (list.getStatus().equals(ResponseEnums.SUCCESS.getCode()));
    }

    @After
    public void delete() {
        ResponseVo delete = shippingService.delete(uid, shippingId);
        log.info("result = {}", delete);
        assert (delete.getStatus().equals(ResponseEnums.SUCCESS.getCode()));
    }
}