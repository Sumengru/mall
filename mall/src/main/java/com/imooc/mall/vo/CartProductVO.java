package com.imooc.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartProductVO {
    private Integer productId;

    private  Integer quantity;

    private  String productName;

    private String productSubtitle;

    private String productMainImage;

    private BigDecimal productPrice;

    private Integer productStatus;

    private Integer productTotalPrice;

    private Integer productStock;

    private Boolean productSeleted;
}
