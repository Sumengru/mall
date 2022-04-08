package com.imooc.mall;

import com.imooc.mall.Exception.UserLoginException;
import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.imooc.mall.consts.MallConst.CURRENT_USER;

@Slf4j
public class UserLoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getAttribute(CURRENT_USER);
        if(user == null){
            log.error("用户未登录");
            throw new UserLoginException();
        }
        return true;
    }
}
