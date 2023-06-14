package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.ProductCategory;
import com.nonit.personalproject.entity.WarehouseArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    private String name;
    private String code;
    private String description;
    private ProductCategory productCategory;
}
