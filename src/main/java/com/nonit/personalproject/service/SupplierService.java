package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.dto.customdto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.customdto.SupplierStatsDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplierService {
    List<SupplierDTO> getAllSupplier();
    SupplierDTO findSupplierById (Long supplierId);
    SupplierDTO createSupplier (Long countryId, SupplierCreateDTO supplierCreateDTO);
    SupplierDTO updateSupplier (Long supplierId, SupplierCreateDTO supplierCreateDTO);
    void deleteSupplier (Long supplierId);
    List<SupplierDTO> findByNameIgnoreCaseContaining (String supplierName);
    List<SupplierStatsDTO> getSupplierAndItsProduct(String inputName);
    List<SupplierStatsDTO> getProductAndItsSuppliers (String inputProductName);
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTime ();
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBetweenDates(LocalDateTime fromDate, LocalDateTime toDate);
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBetweenDates(Long supplierId, LocalDateTime fromDate, LocalDateTime toDate);
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBeforeDate(Long supplierId, LocalDateTime beforeDate);
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBeforeDate(LocalDateTime beforeDate);

}
