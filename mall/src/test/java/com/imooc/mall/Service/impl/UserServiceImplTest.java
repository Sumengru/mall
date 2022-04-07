package com.imooc.mall.Service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.Service.IUserService;
import com.imooc.mall.enums.RoleEnums;
import com.imooc.mall.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@Transactional
public class UserServiceImplTest extends MallApplicationTests {
    @Autowired
    private IUserService userService;
    @Test
    public void register() {
        User user = new User("zhangsan","123456","1719292390@qq.com", RoleEnums.CUNSTOM.getCode());
        userService.register(user);
    }
}