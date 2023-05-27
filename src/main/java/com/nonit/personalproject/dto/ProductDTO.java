package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.ProductCategory;
import com.nonit.personalproject.entity.WarehouseArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productCode;
    private String productDescription;
    private String productCategory;
    private WarehouseArea warehouseArea;
}
