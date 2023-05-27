package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.GoodsDeliveryNote;
import com.nonit.personalproject.entity.IncomingsDetail;
import com.nonit.personalproject.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutcomingsDetailDTO {
    private Long outcomingsId;
    private Double outcomingsAmount;
    private Double productPrice;
    private Double discount;
    private Product product;
    private GoodsDeliveryNote goodsDeliveryNote;
    private IncomingsDetail incomingsDetail;
}
