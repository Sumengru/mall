package com.imooc.mall.Service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.IUserService;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.enums.RoleEnums;
import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@Transactional
public class UserServiceImplTest extends MallApplicationTests {
    private static final String USERNAME = "JACK";
    private static final String PASSWORD = "123456";
    @Autowired
    private IUserService userService;
    @Before
    public void register() {
        User user = new User(USERNAME,PASSWORD,"1719292390@qq.com", RoleEnums.CUNSTOM.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
        ResponseVo<User> responseVo = userService.login(USERNAME,PASSWORD);
        assert(responseVo.getStatus() == ResponseEnums.SUCCESS.getCode());
    }
}