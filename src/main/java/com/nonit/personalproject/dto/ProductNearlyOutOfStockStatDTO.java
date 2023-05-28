package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductNearlyOutOfStockStatDTO {
    private Long productId;
    private String productName;
    private Double totalStockAmount;
}
