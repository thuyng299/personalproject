package com.nonit.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "product_name",unique = true, nullable = false)
    private String productName;
    @Column(name = "product_code", unique = true, nullable = false)
    private String productCode;
    @Column(name = "product_description", length = 1000)
    private String productDescription;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "areaId", nullable = false)
    private WarehouseArea warehouseArea;
}
