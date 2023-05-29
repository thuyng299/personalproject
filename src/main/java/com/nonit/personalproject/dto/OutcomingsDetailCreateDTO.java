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
public class OutcomingsDetailCreateDTO {
    private Double outcomingsAmount;
    private Double productPrice;
    private Double discount;
//    private Long productId;
//    private Long incomingsId;
}
