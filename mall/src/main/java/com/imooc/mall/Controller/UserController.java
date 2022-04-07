package com.imooc.mall.Controller;

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {
    @PostMapping("register")
    public ResponseVo register(@RequestBody User user){
        log.info(user.toString());
        return ResponseVo.success("注册成功");
    }
}
