package com.imooc.mall.enums;

import lombok.Getter;

@Getter
public enum RoleEnums {
    ADMIN(0),
    CUNSTOM(1)
    ;

    RoleEnums(Integer code) {
        this.code = code;
    }

    Integer code;

}
