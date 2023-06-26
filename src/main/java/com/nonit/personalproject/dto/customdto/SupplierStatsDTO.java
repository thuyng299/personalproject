package com.nonit.personalproject.dto.customdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierStatsDTO {
    private Long supplierId;
    private String supplierName;
    private String productName;
    private Double totalPurchaseAmount;
}
