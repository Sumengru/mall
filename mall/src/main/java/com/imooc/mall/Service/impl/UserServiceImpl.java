package com.imooc.mall.Service.impl;

import com.imooc.mall.Service.IUserService;
import com.imooc.mall.dao.UserMapper;
import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.enums.RoleEnums;
import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseVo<User> register(User user) {
        user.setRole(RoleEnums.CUNSTOM.getCode());
        int countByUsername = userMapper.CountByUsername(user.getUsername());
        if(countByUsername > 0){
            return ResponseVo.error(ResponseEnums.USER_EXIST);
        }
        int countByEmail = userMapper.CountByEmail(user.getEmail());
        if(countByEmail > 0){
            return ResponseVo.error(ResponseEnums.EMAIL_EXIST);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getUsername().getBytes(StandardCharsets.UTF_8)));
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0){
            return ResponseVo.error(ResponseEnums.ERROR);
        }
        return ResponseVo.success(user);
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUserName(username);
        if(user == null){
            return ResponseVo.error(ResponseEnums.USER_OR_PASSWORD_ERROR);
        }
        if(!user.getPassword().
                equalsIgnoreCase(DigestUtils.md5DigestAsHex(user.getUsername().getBytes(StandardCharsets.UTF_8)))){
            return ResponseVo.error(ResponseEnums.USER_OR_PASSWORD_ERROR);
        }
        return ResponseVo.success(user);
    }
}
