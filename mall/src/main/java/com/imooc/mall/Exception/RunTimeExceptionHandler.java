package com.imooc.mall.Exception;

import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.imooc.mall.enums.ResponseEnums.ERROR;
import static com.imooc.mall.enums.ResponseEnums.NEED_LOGIN;

@ControllerAdvice
public class RunTimeExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseVo handler(RuntimeException e){
        return ResponseVo.error(ERROR,e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo userLoginHandler(){
        return ResponseVo.error(NEED_LOGIN);
    }
}