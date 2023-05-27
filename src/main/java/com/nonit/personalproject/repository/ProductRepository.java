package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByProductName (String productName);
    Boolean existsByProductCode (String productCode);
    Product findByProductCategory (String productCategory);
    Product findByWarehouseAreaAreaId(Long areaId);
}
