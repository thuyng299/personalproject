package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    List<SupplierDTO> getAllSupplier();
    SupplierDTO findSupplierById (Long supplierId);
    SupplierDTO createSupplier (Long countryId, SupplierCreateDTO supplierCreateDTO);
    void deleteSupplier (Long supplierId);
    SupplierDTO findBySupplierName (String supplierName);
}
