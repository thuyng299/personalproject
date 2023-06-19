package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.Product;
import com.nonit.personalproject.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByName (String productName);
    Boolean existsByCode (String productCode);
    Product findByProductCategory (String productCategory);
    Product findByWarehouseAreaId(Long areaId);
    Optional<Product> findByCode(String code);
}
