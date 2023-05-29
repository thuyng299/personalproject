package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SupplierService {
    List<SupplierDTO> getAllSupplier();
    SupplierDTO findSupplierById (Long supplierId);
    SupplierDTO createSupplier (Long countryId, SupplierCreateDTO supplierCreateDTO);
    void deleteSupplier (Long supplierId);
    SupplierDTO findBySupplierName (String supplierName);
    List<SupplierStatsDTO> getSupplierAndItsProduct(String inputName);
    List<SupplierStatsDTO> getProductAndItsSuppliers (String inputProductName);
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTime ();
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBetweenDates( LocalDate fromDate, LocalDate toDate);
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBetweenDates(Long supplierId, LocalDate fromDate, LocalDate toDate);
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBeforeDate(Long supplierId, LocalDate beforeDate);
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBeforeDate(LocalDate beforeDate);
    SupplierDTO updateSupplier (Long supplierId, SupplierCreateDTO supplierCreateDTO);
}
