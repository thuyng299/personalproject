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
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name",unique = true, nullable = false)
    private String name;
    @Column(name = "product_code", unique = true, nullable = false)
    private String code;
    @Column(name = "product_description", length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private WarehouseArea warehouseArea;
}
