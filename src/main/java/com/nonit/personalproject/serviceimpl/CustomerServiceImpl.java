package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.CustomerAndProductStatsDTO;
import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.dto.CustomerStatsDTO;
import com.nonit.personalproject.entity.Country;
import com.nonit.personalproject.entity.Customer;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.CustomerMapper;
import com.nonit.personalproject.repository.CountryRepository;
import com.nonit.personalproject.repository.CustomerRepository;
import com.nonit.personalproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CountryRepository countryRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()){
            throw WarehouseException.CustomerNotFound();
        }
        return customerMapper.toDtos(customers);
    }

    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(WarehouseException::CustomerNotFound);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO createCustomer(Long countryId, CustomerCreateDTO customerCreateDTO) {
        Country country = countryRepository.findById(countryId).orElseThrow(WarehouseException::CountryNotFound);
        if (customerCreateDTO.getName() == null || customerCreateDTO.getName().isEmpty() || customerCreateDTO.getName().isBlank()){
            throw WarehouseException.badRequest("InvalidName", "Customer name cannot be null!");
        }
        if (customerRepository.existsByName(customerCreateDTO.getName())){
            throw WarehouseException.badRequest("CustomerNameExisted", "Customer name already exists!");
        }
        if (customerCreateDTO.getEmail() == null || customerCreateDTO.getEmail().isBlank() || customerCreateDTO.getEmail().isEmpty()){
            throw WarehouseException.badRequest("InvalidEmail", "Customer email cannot be null!");
        }
        if (customerRepository.existsByEmail(customerCreateDTO.getEmail())){
            throw WarehouseException.badRequest("CustomerEmailExisted", "Customer email already exists!");
        }
        if (customerCreateDTO.getCode() == null || customerCreateDTO.getCode().isBlank() || customerCreateDTO.getCode().isEmpty()){
            throw WarehouseException.badRequest("InvalidCustomerCode", "Customer code cannot be null!");
        }
        if (customerRepository.existsByCode(customerCreateDTO.getCode())){
            throw WarehouseException.badRequest("CustomerCodeExisted", "Customer code is already taken!");
        }
        if (customerRepository.existsByPhone(customerCreateDTO.getPhone())){
            throw WarehouseException.badRequest("CustomerPhoneExisted", "Customer phone is already taken!");
        }
        Customer customer = Customer.builder()
                .name(customerCreateDTO.getName())
                .code(customerCreateDTO.getCode())
                .email(customerCreateDTO.getEmail())
                .phone(customerCreateDTO.getPhone())
                .address(customerCreateDTO.getAddress())
                .status(Boolean.TRUE)
                .country(country)
                .build();
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        log.info("delete customer by id {}", customerId);
        customerRepository.deleteById(customerId);
    }

//    @Override
//    public List<CustomerStatsDTO> getCustomerAndItsProduct(String inputName) {
//        if (inputName == null || inputName.isBlank()){
//            throw WarehouseException.badRequest("InvalidName", "Customer name must not be null!");
//        }
//        inputName = "%" + inputName + "%";
//        return customerRepository.getCustomerAndItsProduct(inputName);
//    }
//
//    @Override
//    public List<CustomerStatsDTO> getProductAndItsCustomers(String inputProductName) {
//        if (inputProductName == null || inputProductName.isBlank()){
//            throw WarehouseException.badRequest("InvalidName", "Product name must not be null!");
//        }
//        inputProductName = "%" + inputProductName + "%";
//        return customerRepository.getProductAndItsCustomers(inputProductName);
//    }
//
//    @Override
//    public List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTime() {
//        return customerRepository.getCustomersAndTotalSalesTime();
//    }
//
//    @Override
//    public List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBetweenDates(LocalDate fromDate, LocalDate toDate) {
//        if (fromDate.isAfter(LocalDate.now()) || toDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
//        }
//        return customerRepository.getCustomersAndTotalSalesTimeBetweenDates(fromDate, toDate);
//    }
//
//    @Override
//    public CustomerAndProductStatsDTO getCustomerAndTotalAmountBetweenDates(Long customerId, LocalDate fromDate, LocalDate toDate) {
//        Customer customer = customerRepository.findById(customerId).orElseThrow(WarehouseException::CustomerNotFound);
//        if (fromDate.isAfter(LocalDate.now()) || toDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
//        }
//        return customerRepository.getCustomerAndTotalAmountBetweenDates(customerId, fromDate, toDate);
//    }
//
//    @Override
//    public CustomerAndProductStatsDTO getCustomerAndTotalAmountBeforeDate(Long customerId, LocalDate beforeDate) {
//        Customer customer = customerRepository.findById(customerId).orElseThrow(WarehouseException::CustomerNotFound);
//        if (beforeDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
//        }
//        return customerRepository.getCustomerAndTotalAmountBeforeDate(customerId, beforeDate);
//    }
//
//    @Override
//    public List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBeforeDate(LocalDate beforeDate) {
//        if (beforeDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
//        }
//        return customerRepository.getCustomersAndTotalSalesTimeBeforeDate(beforeDate);
//    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerCreateDTO customerCreateDTO) {
        log.info("update customer by id {}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(WarehouseException::CustomerNotFound);
        if (customerRepository.existsByName(customerCreateDTO.getName())){
            throw WarehouseException.badRequest("CustomerNameExisted", "Customer name already exists!");
        }
        if (customerRepository.existsByEmail(customerCreateDTO.getEmail())){
            throw WarehouseException.badRequest("CustomerEmailExisted", "Customer email already exists!");
        }
        if (customerRepository.existsByCode(customerCreateDTO.getCode())){
            throw WarehouseException.badRequest("CustomerCodeExisted", "Customer code already exists!!");
        }
        if (customerRepository.existsByPhone(customerCreateDTO.getPhone())){
            throw WarehouseException.badRequest("CustomerPhoneExisted", "Customer phone already exists!");
        }
        customerMapper.mapFromDto(customerCreateDTO, customer);
        return customerMapper.toDto(customerRepository.save(customer));
    }
}
