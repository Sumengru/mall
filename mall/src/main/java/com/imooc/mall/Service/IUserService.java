package com.imooc.mall.Service;

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;

public interface IUserService {
    ResponseVo register(User user);

    void login(User user);
}
