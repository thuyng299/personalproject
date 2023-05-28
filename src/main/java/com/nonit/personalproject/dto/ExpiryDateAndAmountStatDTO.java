package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpiryDateAndAmountStatDTO {
    private Long grnId;
    private Long productId;
    private String productName;
    private Long countDaysBeforeExpire;
    private Double totalStockAmount;
}
