package com.imooc.mall.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnums {
    ON_SALE(1,"在售"),
    OFF_SAFE(2,"下架"),
    DELETE(3,"删除");
    Integer code;

    ProductStatusEnums(Integer code, String 删除) {
        this.code = code;
    }
}
