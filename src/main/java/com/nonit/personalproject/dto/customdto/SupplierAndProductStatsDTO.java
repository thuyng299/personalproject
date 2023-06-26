package com.nonit.personalproject.dto.customdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAndProductStatsDTO {
    private Long supplierId;
    private String supplierName;
    private Long totalPurchaseTime;
    private Double totalPurchaseAmount;
}
