package com.nonit.personalproject.dto.customstatsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceStatsDTO {
    private Long productId;
    private String productName;
    private Double totalAmount;
    private Double totalPrice;
}
