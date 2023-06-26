package com.nonit.personalproject.dto.customdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAndProductStatsDTO {
    private Long customerId;
    private String customerName;
    private Long totalSalesTime;
    private Double totalSalesAmount;
}
