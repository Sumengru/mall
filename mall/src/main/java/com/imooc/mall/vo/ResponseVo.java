package com.imooc.mall.vo;

public class ResponseVo<T> {
    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private Integer status;
    private String msg;
    private T data;
    public static <T>ResponseVo<T> success(String msg){
        return new ResponseVo<>(0,msg);
    }
}
