package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockAmountOfCategoryStatDTO {
    private Long productId;
    private String productCode;
    private ProductCategory productCategory;
    private Double totalStockAmount;
}
