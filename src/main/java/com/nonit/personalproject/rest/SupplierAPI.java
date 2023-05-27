package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/suppliers")
public interface SupplierAPI {
    @GetMapping
    ResponseEntity<List<SupplierDTO>> getAllSupplier();
    @GetMapping("/{supplierId}")
    ResponseEntity<SupplierDTO> findSupplierById(@PathVariable("supplierId") Long supplierId);
    @PostMapping("/{countryId}")
    ResponseEntity<SupplierDTO> createSupplier(@PathVariable("countryId") Long countryId,
                                               @RequestBody SupplierCreateDTO supplierCreateDTO);
    @DeleteMapping("/{supplierId}")
    ResponseEntity<Void> deleteSupplier(@PathVariable("supplierId") Long supplierId);
    @GetMapping("/name")
    ResponseEntity<SupplierDTO> findBySupplierName(@RequestParam("supplierName") String supplierName);
    @GetMapping("/supplier-products") // localhost:8080/suppliers/supplier-products?inputName=solagri
    ResponseEntity<List<SupplierStatsDTO>> getSupplierAndItsProduct(@RequestParam("inputName") String inputName);
    @GetMapping("/product-suppliers") // localhost:8080/suppliers/product-suppliers?inputProductName=ww210
    ResponseEntity<List<SupplierStatsDTO>> getProductAndItsSuppliers (@RequestParam("inputProductName") String inputProductName);
    @GetMapping("/supplierswithpurchasetime") // localhost:8080/suppliers/supplierswithpurchasetime
    ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTime();
}
