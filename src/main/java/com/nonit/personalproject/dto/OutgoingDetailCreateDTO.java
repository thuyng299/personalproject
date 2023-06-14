package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutgoingDetailCreateDTO {
    private Double outcomingsAmount;
    private Double productPrice;
    private Double discount;
//    private Long productId;
//    private Long incomingsId;
}
