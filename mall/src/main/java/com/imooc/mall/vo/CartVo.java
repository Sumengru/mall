package com.imooc.mall.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartVo {
    private List<CartProductVO> productVOList;
    private Boolean seletedAll;
    private BigDecimal cartTotalPrice;
    private Integer cartTotalQuantity;
}
