package com.imooc.mall.Service;

import com.imooc.mall.pojo.User;

public interface IUserService {
    void register(User user);

    void login(User user);
}
