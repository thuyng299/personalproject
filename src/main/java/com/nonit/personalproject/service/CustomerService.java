package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.CustomerAndProductStatsDTO;
import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.dto.CustomerStatsDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();
    CustomerDTO findCustomerById (Long customerId);
    CustomerDTO createCustomer (Long countryId, CustomerCreateDTO customerCreateDTO);
    void deleteCustomer (Long customerId);
    List<CustomerStatsDTO> getCustomerAndItsProduct(String inputName);
    List<CustomerStatsDTO> getProductAndItsCustomers (String inputProductName);
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTime();
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBetweenDates (LocalDate fromDate, LocalDate toDate);
    CustomerAndProductStatsDTO getCustomerAndTotalAmountBetweenDates (Long customerId, LocalDate fromDate, LocalDate toDate);
    CustomerAndProductStatsDTO getCustomerAndTotalAmountBeforeDate (Long customerId, LocalDate beforeDate);
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBeforeDate (LocalDate beforeDate);
}
