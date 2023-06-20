package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.customstatsdto.CustomerAndProductStatsDTO;
import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.dto.customstatsdto.CustomerStatsDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();
    CustomerDTO findCustomerById (Long customerId);
    CustomerDTO createCustomer (Long countryId, CustomerCreateDTO customerCreateDTO);
    CustomerDTO updateCustomer (Long customerId, CustomerCreateDTO customerCreateDTO);
    void deleteCustomer (Long customerId);
    List<CustomerDTO> findByNameContainingIgnoreCase (String customerName);
    List<CustomerStatsDTO> getCustomerAndItsProduct(String inputName);
    List<CustomerStatsDTO> getProductAndItsCustomers (String inputProductName);
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTime();
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBetweenDates (LocalDateTime fromDate, LocalDateTime toDate);
    CustomerAndProductStatsDTO getCustomerAndTotalAmountBetweenDates (Long customerId, LocalDateTime fromDate, LocalDateTime toDate);
    CustomerAndProductStatsDTO getCustomerAndTotalAmountBeforeDate (Long customerId, LocalDateTime beforeDate);
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBeforeDate (LocalDateTime beforeDate);

}
