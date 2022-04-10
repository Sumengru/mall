package com.imooc.mall.Exception;

import com.imooc.mall.enums.ResponseEnums;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.imooc.mall.enums.ResponseEnums.*;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo noargumentHandler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        return ResponseVo.error(NEED_LOGIN,bindingResult.getFieldError().getField()  + bindingResult.getFieldError().getDefaultMessage());
    }

}
