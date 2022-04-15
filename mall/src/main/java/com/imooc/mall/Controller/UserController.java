package com.imooc.mall.Controller;

import com.imooc.mall.Service.IUserService;
import com.imooc.mall.form.UserRegisterForm;
import com.imooc.mall.form.UserLoginForm;
import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.imooc.mall.consts.MallConst.CURRENT_USER;
import static com.imooc.mall.enums.ResponseEnums.*;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userForm){
        //error();
        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        return userService.register(user);
    }

    @PostMapping("login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  HttpSession session){
        ResponseVo<User> userResponseVo= userService.login(userLoginForm.getUsername(),userLoginForm.getPassword());
        session.setAttribute(CURRENT_USER,userResponseVo.getData());
        return userResponseVo;
    }

    @GetMapping("msg")
    public ResponseVo<User> getUserMsg(HttpSession session){
        User user = (User) session.getAttribute(CURRENT_USER);
        return ResponseVo.success(user);
    }
    void error(){
        throw new RuntimeException("发生错误！");
    }

    @PostMapping("loginout")
    public ResponseVo<User> loginOut(HttpSession session){
        session.removeAttribute(CURRENT_USER);
        return ResponseVo.successByMsg();
    }
}
