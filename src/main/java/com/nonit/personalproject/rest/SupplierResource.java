package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.customstatsdto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import com.nonit.personalproject.dto.customstatsdto.SupplierStatsDTO;
import com.nonit.personalproject.serviceimpl.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupplierResource implements SupplierAPI{
    private final SupplierServiceImpl supplierServiceImpl;

    @Override
    public ResponseEntity<List<SupplierDTO>> getAllSupplier() {
        return ResponseEntity.ok(supplierServiceImpl.getAllSupplier());
    }

    @Override
    public ResponseEntity<SupplierDTO> findSupplierById(Long supplierId) {
        return ResponseEntity.ok(supplierServiceImpl.findSupplierById(supplierId));
    }

    @Override
    public ResponseEntity<SupplierDTO> createSupplier(Long countryId, SupplierCreateDTO supplierCreateDTO) {
        SupplierDTO createdSupplierDTO = supplierServiceImpl.createSupplier(countryId, supplierCreateDTO);
        return ResponseEntity.created(URI.create("/suppliers" + createdSupplierDTO.getId())).body(createdSupplierDTO);
    }

    @Override
    public ResponseEntity<SupplierDTO> updateSupplier(Long supplierId, SupplierCreateDTO supplierCreateDTO) {
        return ResponseEntity.ok().body(supplierServiceImpl.updateSupplier(supplierId,supplierCreateDTO));
    }

    @Override
    public ResponseEntity<Void> deleteSupplier(Long supplierId) {
        supplierServiceImpl.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<SupplierDTO>> findByNameIgnoreCaseContaining(String supplierName) {
        return ResponseEntity.ok(supplierServiceImpl.findByNameIgnoreCaseContaining(supplierName));
    }

    @Override
    public ResponseEntity<List<SupplierStatsDTO>> getSupplierAndItsProduct(String inputName) {
        return ResponseEntity.ok(supplierServiceImpl.getSupplierAndItsProduct(inputName));
    }

    @Override
    public ResponseEntity<List<SupplierStatsDTO>> getProductAndItsSuppliers(String inputProductName) {
        return ResponseEntity.ok(supplierServiceImpl.getProductAndItsSuppliers(inputProductName));
    }

    @Override
    public ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTime() {
        return ResponseEntity.ok(supplierServiceImpl.getSuppliersAndTotalPurchaseTime());
    }

    @Override
    public ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBetweenDates(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.MAX);
        return ResponseEntity.ok(supplierServiceImpl.getSuppliersAndTotalPurchaseTimeBetweenDates(fromDateTime, toDateTime));
    }

    @Override
    public ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBetweenDates(Long supplierId, LocalDate fromDate, LocalDate toDate) {
        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.MAX);
        return ResponseEntity.ok(supplierServiceImpl.getSupplierAndTotalAmountBetweenDates(supplierId, fromDateTime, toDateTime));
    }

    @Override
    public ResponseEntity<SupplierAndProductStatsDTO> getSupplierAndTotalAmountBeforeDate(Long supplierId, LocalDate beforeDate) {
        LocalDateTime beforeDateTime = LocalDateTime.of(beforeDate, LocalTime.MAX);
        return ResponseEntity.ok(supplierServiceImpl.getSupplierAndTotalAmountBeforeDate(supplierId, beforeDateTime));
    }

    @Override
    public ResponseEntity<List<SupplierAndProductStatsDTO>> getSuppliersAndTotalPurchaseTimeBeforeDate(LocalDate beforeDate) {
        LocalDateTime beforeDateTime = LocalDateTime.of(beforeDate, LocalTime.MAX);
        return ResponseEntity.ok(supplierServiceImpl.getSuppliersAndTotalPurchaseTimeBeforeDate(beforeDateTime));
    }
}
