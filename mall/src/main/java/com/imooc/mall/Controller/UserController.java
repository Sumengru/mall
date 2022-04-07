package com.imooc.mall.Controller;

import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.form.UserForm;
import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.imooc.mall.enums.ResponseEnums.NEED_LOGIN;
import static com.imooc.mall.enums.ResponseEnums.PARAM_ERROR;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {
    @PostMapping("register")
    public ResponseVo register(@Valid @RequestBody UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(bindingResult.getFieldError().getDefaultMessage());
        }
        //return ResponseVo.success();
        return ResponseVo.error(PARAM_ERROR,bindingResult);
    }
}
