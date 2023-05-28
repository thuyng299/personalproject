package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/suppliersbetweendates") // localhost:8080/suppliers/suppliersbetweendates?fromDate=2022-01-21&toDate=2022-07-21
    ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBetweenDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
                                                                                                  @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
    @GetMapping("/supplierandtotalamountbetweendates") // localhost:8080/suppliers/supplierandtotalamountbetweendates?supplierId=11&fromDate=2022-01-21&toDate=2022-07-21
    ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBetweenDates(@RequestParam("supplierId") Long supplierId,
                                                                                    @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                                                     @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
    @GetMapping("/supplierandtotalamountbeforedate/{supplierId}") // localhost:8080/suppliers/supplierandtotalamountbeforedate/11?beforeDate=2022-07-21
    ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBeforeDate(@PathVariable("supplierId")Long supplierId,
                                                                                   @RequestParam("beforeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate);
    @GetMapping("/suppliersbeforedate") // localhost:8080/suppliers/suppliersbeforedate?beforeDate=2022-02-21
    ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBeforeDate(@RequestParam("beforeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate);
}
