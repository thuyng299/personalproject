package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAndProductStatsDTO {
    private Long customerId;
    private String customerName;
    private Long totalPurchaseTime;
    private Double totalSalesAmount;
    private Double totalPrice;
}
