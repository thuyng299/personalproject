package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.serviceimpl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerResource implements CustomerAPI{
    private final CustomerServiceImpl customerServiceImpl;

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomer() {
        return ResponseEntity.ok(customerServiceImpl.getAllCustomer());
    }

    @Override
    public ResponseEntity<CustomerDTO> findCustomerById(Long customerId) {
        return ResponseEntity.ok(customerServiceImpl.findCustomerById(customerId));
    }

    @Override
    public ResponseEntity<CustomerDTO> createCustomer(Long countryId, CustomerCreateDTO customerCreateDTO) {
         CustomerDTO createdcustomerDTO = customerServiceImpl.createCustomer(countryId, customerCreateDTO);
         return ResponseEntity.created(URI.create("/customers" + createdcustomerDTO.getCustomerId())).body(createdcustomerDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        customerServiceImpl.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CustomerStatsDTO>> getCustomerAndItsProduct(String inputName) {
        return ResponseEntity.ok(customerServiceImpl.getCustomerAndItsProduct(inputName));
    }

    @Override
    public ResponseEntity<List<CustomerStatsDTO>> getProductAndItsCustomers(String inputProductName) {
        return ResponseEntity.ok(customerServiceImpl.getProductAndItsCustomers(inputProductName));
    }

    @Override
    public ResponseEntity<List<CustomerAndProductStatsDTO>> getCustomersAndTotalSalesTime() {
        return ResponseEntity.ok(customerServiceImpl.getCustomersAndTotalSalesTime());
    }

    @Override
    public ResponseEntity<List<CustomerAndProductStatsDTO>> getCustomersAndTotalSalesTimeBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return ResponseEntity.ok(customerServiceImpl.getCustomersAndTotalSalesTimeBetweenDates(fromDate, toDate));
    }

    @Override
    public ResponseEntity<CustomerAndProductStatsDTO> getCustomerAndTotalAmountBetweenDates(Long customerId, LocalDate fromDate, LocalDate toDate) {
        return ResponseEntity.ok(customerServiceImpl.getCustomerAndTotalAmountBetweenDates(customerId, fromDate, toDate));
    }

    @Override
    public ResponseEntity<CustomerAndProductStatsDTO> getCustomerAndTotalAmountBeforeDate(Long customerId, LocalDate beforeDate) {
        return ResponseEntity.ok(customerServiceImpl.getCustomerAndTotalAmountBeforeDate(customerId, beforeDate));
    }

    @Override
    public ResponseEntity<List<CustomerAndProductStatsDTO>> getCustomersAndTotalSalesTimeBeforeDate(LocalDate beforeDate) {
        return ResponseEntity.ok(customerServiceImpl.getCustomersAndTotalSalesTimeBeforeDate(beforeDate));
    }


}
