package com.imooc.mall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnums {
    ERROR(-1,"服务端异常"),

    SUCCESS(0,"成功"),

    PASSWORD_ERROR(1,"密码错误"),

    USER_EXIST(1,"用户已存在"),

    EMAIL_EXIST(2,"邮箱已存在"),

    NEED_LOGIN(10,"未登录"),

    USER_OR_PASSWORD_ERROR(11,"用户名或密码错误"),

    PARAM_ERROR(3,"参数错误");

    ResponseEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    Integer code;
    String desc;
}
