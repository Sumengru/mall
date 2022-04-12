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

    private BigDecimal productTotalPrice;

    private Integer productStock;

    private Boolean productSeleted;

    public CartProductVO(Integer productId, Integer quantity, String productName, String productSubtitle, String productMainImage, BigDecimal productPrice, Integer productStatus, BigDecimal productTotalPrice, Boolean productSeleted) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productSubtitle = productSubtitle;
        this.productMainImage = productMainImage;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productTotalPrice = productTotalPrice;
        this.productStock = productStock;
        this.productSeleted = productSeleted;
    }
}
