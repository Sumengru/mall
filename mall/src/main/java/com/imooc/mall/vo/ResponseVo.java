package com.imooc.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.mall.enums.ResponseEnums;
import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }
    private Integer status;
    private String msg;
    private T data;
    public static <T>ResponseVo<T> successByMsg(){
        return new ResponseVo<>(ResponseEnums.SUCCESS.getCode(),ResponseEnums.SUCCESS.getDesc());
    }

    public static <T>ResponseVo<T> success(T data){
        return new ResponseVo<>(ResponseEnums.SUCCESS.getCode(),data);
    }

    public static <T>ResponseVo<T> error(ResponseEnums responseEnums){
        return new ResponseVo<>(responseEnums.getCode(),responseEnums.getDesc());
    }

    public static <T>ResponseVo<T> error(ResponseEnums responseEnums, BindingResult bindingResult){
        return new ResponseVo<>(responseEnums.getCode(),bindingResult.getFieldError().getField() + " "+ bindingResult.getFieldError().getDefaultMessage());
    }

    public static <T>ResponseVo<T> error(ResponseEnums responseEnums, String msg){
        return new ResponseVo<>(responseEnums.getCode(),msg);
    }
}
