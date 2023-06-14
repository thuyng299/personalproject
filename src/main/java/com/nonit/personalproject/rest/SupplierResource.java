//package com.nonit.personalproject.rest;
//
//import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
//import com.nonit.personalproject.dto.SupplierCreateDTO;
//import com.nonit.personalproject.dto.SupplierDTO;
//import com.nonit.personalproject.dto.SupplierStatsDTO;
//import com.nonit.personalproject.serviceimpl.SupplierServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.net.URI;
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class SupplierResource implements SupplierAPI{
//    private final SupplierServiceImpl supplierServiceImpl;
//
//    @Override
//    public ResponseEntity<List<SupplierDTO>> getAllSupplier() {
//        return ResponseEntity.ok(supplierServiceImpl.getAllSupplier());
//    }
//
//    @Override
//    public ResponseEntity<SupplierDTO> findSupplierById(Long supplierId) {
//        return ResponseEntity.ok(supplierServiceImpl.findSupplierById(supplierId));
//    }
//
//    @Override
//    public ResponseEntity<SupplierDTO> createSupplier(Long countryId, SupplierCreateDTO supplierCreateDTO) {
//        SupplierDTO createdSupplierDTO = supplierServiceImpl.createSupplier(countryId, supplierCreateDTO);
//        return ResponseEntity.created(URI.create("/suppliers" + createdSupplierDTO.getId())).body(createdSupplierDTO);
//    }
//
//    @Override
//    public ResponseEntity<Void> deleteSupplier(Long supplierId) {
//        supplierServiceImpl.deleteSupplier(supplierId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @Override
//    public ResponseEntity<SupplierDTO> findBySupplierName(String supplierName) {
//        return ResponseEntity.ok(supplierServiceImpl.findBySupplierName(supplierName));
//    }
//
//    @Override
//    public ResponseEntity<List<SupplierStatsDTO>> getSupplierAndItsProduct(String inputName) {
//        return ResponseEntity.ok(supplierServiceImpl.getSupplierAndItsProduct(inputName));
//    }
//
//    @Override
//    public ResponseEntity<List<SupplierStatsDTO>> getProductAndItsSuppliers(String inputProductName) {
//        return ResponseEntity.ok(supplierServiceImpl.getProductAndItsSuppliers(inputProductName));
//    }
//
//    @Override
//    public ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTime() {
//        return ResponseEntity.ok(supplierServiceImpl.getSuppliersAndTotalPurchaseTime());
//    }
//
//    @Override
//    public ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBetweenDates(LocalDate fromDate, LocalDate toDate) {
//        return ResponseEntity.ok(supplierServiceImpl.getSuppliersAndTotalPurchaseTimeBetweenDates(fromDate, toDate));
//    }
//
//    @Override
//    public ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBetweenDates(Long supplierId, LocalDate fromDate, LocalDate toDate) {
//        return ResponseEntity.ok(supplierServiceImpl.getSupplierAndTotalAmountBetweenDates(supplierId, fromDate, toDate));
//    }
//
//    @Override
//    public ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBeforeDate(Long supplierId, LocalDate beforeDate) {
//        return ResponseEntity.ok(supplierServiceImpl.getSupplierAndTotalAmountBeforeDate(supplierId, beforeDate));
//    }
//
//    @Override
//    public ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBeforeDate(LocalDate beforeDate) {
//        return ResponseEntity.ok(supplierServiceImpl.getSuppliersAndTotalPurchaseTimeBeforeDate(beforeDate));
//    }
//
//    @Override
//    public ResponseEntity<SupplierDTO> updateSupplier(Long supplierId, SupplierCreateDTO supplierCreateDTO) {
//        return ResponseEntity.ok().body(supplierServiceImpl.updateSupplier(supplierId,supplierCreateDTO));
//    }
//}
