package com.imooc.mall.Service.impl;

import com.imooc.mall.Service.IUserService;
import com.imooc.mall.dao.UserMapper;
import com.imooc.mall.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(User user) {
        int countByUsername = userMapper.CountByUsername(user.getUsername());
        if(countByUsername > 0){
            throw new RuntimeException("该用户已经注册！");
        }
        int countByEmail = userMapper.CountByEmail(user.getEmail());
        if(countByEmail > 0){
            throw new RuntimeException("该邮箱已经注册！");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getUsername().getBytes(StandardCharsets.UTF_8)));
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0){
            throw new RuntimeException("注册失败");
        }
    }

    @Override
    public void login(User user) {

    }
}
