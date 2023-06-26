package com.nonit.personalproject.dto.customdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatsDTO {
    private Long customerId;
    private String customerName;
    private String productName;
    private Double totalSalesAmount;
}
