package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierName (String supplierName);
    Boolean existsBySupplierCode (String supplierCode);
    Boolean existsBySupplierName (String supplierName);
    Boolean existsBySupplierEmail (String supplierEmail);
}
