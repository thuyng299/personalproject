package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.customdto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import com.nonit.personalproject.dto.customdto.SupplierStatsDTO;
import com.nonit.personalproject.entity.Country;
import com.nonit.personalproject.entity.Supplier;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.SupplierMapper;
import com.nonit.personalproject.repository.CountryRepository;
import com.nonit.personalproject.repository.SupplierRepository;
import com.nonit.personalproject.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (supplierCreateDTO.getName() == null || supplierCreateDTO.getName().trim().isBlank() || supplierCreateDTO.getName().isEmpty()){
            throw WarehouseException.badRequest("InvalidName", "Supplier name cannot be null!");
        }
        if (supplierRepository.existsByName(supplierCreateDTO.getName())){
            throw WarehouseException.badRequest("SupplierNameExisted", "Supplier name is already taken!");
        }
        if (supplierCreateDTO.getEmail() == null || supplierCreateDTO.getEmail().trim().isBlank() || supplierCreateDTO.getEmail().isEmpty()){
            throw WarehouseException.badRequest("InvalidEmail", "Supplier email cannot be null!");
        }
        if (supplierRepository.existsByEmail(supplierCreateDTO.getEmail())){
            throw WarehouseException.badRequest("SupplierEmailExisted", "Supplier email is already taken!");
        }
        if (supplierCreateDTO.getCode() == null || supplierCreateDTO.getCode().isBlank() || supplierCreateDTO.getCode().isEmpty()){
            throw WarehouseException.badRequest("InvalidCode", "Supplier code cannot be null!");
        }
        if (supplierRepository.existsByCode(supplierCreateDTO.getCode())){
            throw WarehouseException.badRequest("SupplierCodeExisted", "Supplier code is already taken!");
        }
        if (supplierRepository.existsByPhone(supplierCreateDTO.getPhone())){
            throw WarehouseException.badRequest("SupplierPhoneExisted", "Supplier phone is already taken!");
        }

        Supplier supplier = Supplier.builder()
                .name(supplierCreateDTO.getName())
                .code(supplierCreateDTO.getCode())
                .email(supplierCreateDTO.getEmail())
                .phone(supplierCreateDTO.getPhone())
                .address(supplierCreateDTO.getAddress())
                .status(Boolean.TRUE)
                .country(country)
                .build();
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(supplier);
    }

    @Override
    public SupplierDTO updateSupplier(Long supplierId, SupplierCreateDTO supplierCreateDTO) {
        log.info("update supplier by id {}", supplierId);
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(WarehouseException::SupplierNotFound);
        if (supplierRepository.existsByName(supplierCreateDTO.getName())){
            throw WarehouseException.badRequest("SupplierNameExisted", "Supplier name already exists!");
        }
        if (supplierRepository.existsByEmail(supplierCreateDTO.getEmail())){
            throw WarehouseException.badRequest("SupplierEmailExisted", "Supplier email already exists!");
        }
        if (supplierRepository.existsByCode(supplierCreateDTO.getCode())){
            throw WarehouseException.badRequest("SupplierCodeExisted", "Supplier code already exists!");
        }
        if (supplierRepository.existsByPhone(supplierCreateDTO.getPhone())){
            throw WarehouseException.badRequest("SupplierPhoneExisted", "Supplier phone already exists!");
        }
        supplierMapper.mapFromDto(supplierCreateDTO,supplier);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        log.info("delete supplier by id {}", supplierId);
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public List<SupplierDTO> findByNameIgnoreCaseContaining(String supplierName) {
        List<Supplier> supplier = supplierRepository.findByNameIgnoreCaseContaining(supplierName);
        if (supplierName == null || supplierName.trim().isBlank() || supplierName.isEmpty()){
            throw WarehouseException.badRequest("InvalidName", "Supplier name cannot be null!");
        }
        return supplierMapper.toDtos(supplier);
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

    @Override
    public List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBetweenDates(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate.isAfter(LocalDateTime.now()) || toDate.isAfter(LocalDateTime.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDateTime.now());
        }
        return supplierRepository.getSuppliersAndTotalPurchaseTimeBetweenDates(fromDate, toDate);
    }

    @Override
    public SupplierAndProductStatsDTO getSupplierAndTotalAmountBetweenDates(Long supplierId, LocalDateTime fromDate, LocalDateTime toDate) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(WarehouseException::SupplierNotFound);
        if (fromDate.isAfter(LocalDateTime.now()) || toDate.isAfter(LocalDateTime.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDateTime.now());
        }
        return supplierRepository.getSupplierAndTotalAmountBetweenDates(supplierId, fromDate, toDate);
    }

    @Override
    public SupplierAndProductStatsDTO getSupplierAndTotalAmountBeforeDate(Long supplierId, LocalDateTime beforeDate) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(WarehouseException::SupplierNotFound);
        if (beforeDate.isAfter(LocalDateTime.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDateTime.now());
        }
        return supplierRepository.getSupplierAndTotalAmountBeforeDate(supplierId, beforeDate);
    }

    @Override
    public List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBeforeDate(LocalDateTime beforeDate) {
        if (beforeDate.isAfter(LocalDateTime.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDateTime.now());
        }
        return supplierRepository.getSuppliersAndTotalPurchaseTimeBeforeDate(beforeDate);
    }
}
