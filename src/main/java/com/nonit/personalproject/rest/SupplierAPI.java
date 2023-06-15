package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/suppliers")
@PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
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
    ResponseEntity<SupplierDTO> findByName(@RequestParam("supplierName") String supplierName);

//    @GetMapping("/supplier-products") // localhost:8080/suppliers/supplier-products?inputName=solagri
//    ResponseEntity<List<SupplierStatsDTO>> getSupplierAndItsProduct(@RequestParam("inputName") String inputName);
//
//    @GetMapping("/product-suppliers") // localhost:8080/suppliers/product-suppliers?inputProductName=ww210
//    ResponseEntity<List<SupplierStatsDTO>> getProductAndItsSuppliers (@RequestParam("inputProductName") String inputProductName);
//
//    @GetMapping("/suppliers-purchasetime") // localhost:8080/suppliers/suppliers-purchasetime
//    ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTime();
//
//    @GetMapping("/suppliers-fromdate-todate") // localhost:8080/suppliers/suppliers-fromdate-todate?fromDate=2022-01-21&toDate=2022-07-21
//    ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBetweenDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
//                                                                                                  @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
//
//    @GetMapping("/supplier-totalamount-fromdate-todate") // localhost:8080/suppliers/supplier-totalamount-fromdate-todate?supplierId=11&fromDate=2022-01-21&toDate=2022-07-21
//    ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBetweenDates(@RequestParam("supplierId") Long supplierId,
//                                                                                    @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
//                                                                                     @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
//
//    @GetMapping("/supplier-totalamount-beforedate/{supplierId}") // localhost:8080/suppliers/supplier-totalamount-beforedate/11?beforeDate=2022-07-21
//    ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBeforeDate(@PathVariable("supplierId")Long supplierId,
//                                                                                   @RequestParam("beforeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate);
//
//    @GetMapping("/suppliers-beforedate") // localhost:8080/suppliers/suppliers-beforedate?beforeDate=2022-02-21
//    ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBeforeDate(@RequestParam("beforeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate);

    @PutMapping("/{supplierId}")
    ResponseEntity<SupplierDTO> updateSupplier (@PathVariable("supplierId") Long supplierId,
                                                @RequestBody SupplierCreateDTO supplierCreateDTO);
}
