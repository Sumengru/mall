package com.imooc.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.mall.enums.ResponseEnums;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ResponseVo<T> {
    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private Integer status;
    private String msg;
    private T data;
    public static <T>ResponseVo<T> success(){
        return new ResponseVo<>(ResponseEnums.SUCCESS.getCode(),ResponseEnums.SUCCESS.getDesc());
    }

    public static <T>ResponseVo<T> error(ResponseEnums responseEnums){
        return new ResponseVo<>(responseEnums.getCode(),responseEnums.getDesc());
    }
}
