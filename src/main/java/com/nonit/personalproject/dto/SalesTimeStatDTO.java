package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesTimeStatDTO {
    private Long productId;
    private String productName;
    private Long totalSalesTime;
    private Double totalSalesAmount;
}
