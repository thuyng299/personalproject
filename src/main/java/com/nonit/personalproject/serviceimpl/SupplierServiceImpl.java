package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import com.nonit.personalproject.entity.Country;
import com.nonit.personalproject.entity.Product;
import com.nonit.personalproject.entity.Supplier;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.SupplierMapper;
import com.nonit.personalproject.repository.CountryRepository;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.repository.SupplierRepository;
import com.nonit.personalproject.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final CountryRepository countryRepository;
    private final SupplierMapper supplierMapper = SupplierMapper.INSTANCE;

    @Override
    public List<SupplierDTO> getAllSupplier() {
        List<Supplier> suppliers = supplierRepository.findAll();
        if (suppliers.isEmpty()){
            throw WarehouseException.SupplierNotFound();
        }
        return supplierMapper.toDtos(suppliers);
    }

    @Override
    public SupplierDTO findSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(WarehouseException::SupplierNotFound);
        return supplierMapper.toDto(supplier);
    }

    @Override
    public SupplierDTO createSupplier(Long countryId, SupplierCreateDTO supplierCreateDTO) {
        Country country = countryRepository.findById(countryId).orElseThrow(WarehouseException::CountryNotFound);
        if (supplierCreateDTO.getSupplierName() == null || supplierCreateDTO.getSupplierName().trim().isBlank() || supplierCreateDTO.getSupplierName().isEmpty()){
            throw WarehouseException.badRequest("InvalidName", "Supplier name cannot be null!");
        }
        if (supplierRepository.existsBySupplierName(supplierCreateDTO.getSupplierName())){
            throw WarehouseException.badRequest("SupplierNameExisted", "Supplier name already exists!");
        }
        if (supplierCreateDTO.getSupplierEmail() == null || supplierCreateDTO.getSupplierEmail().trim().isBlank() || supplierCreateDTO.getSupplierEmail().isEmpty()){
            throw WarehouseException.badRequest("InvalidEmail", "Supplier email cannot be null!");
        }
        if (supplierRepository.existsBySupplierEmail(supplierCreateDTO.getSupplierEmail())){
            throw WarehouseException.badRequest("SupplierEmailExisted", "Supplier email already exists!");
        }
        if (supplierCreateDTO.getSupplierCode() == null || supplierCreateDTO.getSupplierCode().isBlank() || supplierCreateDTO.getSupplierCode().isEmpty()){
            throw WarehouseException.badRequest("InvalidCode", "Supplier code cannot be null!");
        }
        if (supplierRepository.existsBySupplierCode(supplierCreateDTO.getSupplierCode())){
            throw WarehouseException.badRequest("SupplierCodeExisted", "Supplier code is already taken!");
        }

        Supplier supplier = Supplier.builder()
                .supplierName(supplierCreateDTO.getSupplierName())
                .supplierCode(supplierCreateDTO.getSupplierCode())
                .supplierEmail(supplierCreateDTO.getSupplierEmail())
                .supplierPhone(supplierCreateDTO.getSupplierPhone())
                .supplierAddress(supplierCreateDTO.getSupplierAddress())
                .country(country)
                .build();
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(supplier);
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        log.info("delete supplier by id {}", supplierId);
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public SupplierDTO findBySupplierName(String supplierName) {
        Supplier supplier = supplierRepository.findBySupplierName(supplierName);
        if (supplierName == null || supplierName.trim().isBlank() || supplierName.isEmpty()){
            throw WarehouseException.badRequest("InvalidName", "Supplier name cannot be null!");
        }
        return supplierMapper.toDto(supplier);
    }

    @Override
    public List<SupplierStatsDTO> getSupplierAndItsProduct(String inputName) {
        if (inputName == null || inputName.isBlank()){
            throw WarehouseException.badRequest("InvalidName", "Supplier name must not be null!");
        }
        inputName = "%" + inputName + "%";
        return supplierRepository.getSupplierAndItsProduct(inputName);
    }

    @Override
    public List<SupplierStatsDTO> getProductAndItsSuppliers (String inputProductName) {
        if (inputProductName == null || inputProductName.isBlank()){
            throw WarehouseException.badRequest("InvalidName", "Product name must not be null!");
        }
        inputProductName = "%" + inputProductName + "%";
        return supplierRepository.getProductAndItsSuppliers(inputProductName);
    }

    @Override
    public List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTime() {
        return supplierRepository.getSuppliersAndTotalPurchaseTime();
    }
}
