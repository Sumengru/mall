package com.imooc.mall.pojo;

import lombok.Data;

@Data
public class Cart {

    public Cart(Integer productId, Integer productStock, Boolean productSeleted) {
        this.productId = productId;
        this.quantity = productStock;
        this.productSeleted = productSeleted;
    }

    public Cart() {
    }

    private Integer productId;

    private Integer quantity;

    private Boolean productSeleted;


}
