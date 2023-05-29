package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.CustomerAndProductStatsDTO;
import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.dto.CustomerStatsDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/customers")
public interface CustomerAPI {
    @GetMapping
    ResponseEntity<List<CustomerDTO>> getAllCustomer();
    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDTO> findCustomerById(@PathVariable("customerId") Long customerId);
    @PostMapping("/{countryId}")
    ResponseEntity<CustomerDTO> createCustomer(@PathVariable("countryId")Long countryId,
                                               @RequestBody CustomerCreateDTO customerCreateDTO);
    @DeleteMapping("/{customerId}")
    ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long customerId);
    @GetMapping("/customer-products") // localhost:8080/customers/customer-products?inputName=uab
    ResponseEntity<List<CustomerStatsDTO>> getCustomerAndItsProduct(@RequestParam("inputName") String inputName);
    @GetMapping("/product-customers") // localhost:8080/customers/product-customers?inputProductName=cheese
    ResponseEntity<List<CustomerStatsDTO>> getProductAndItsCustomers(@RequestParam("inputProductName") String inputProductName);
    @GetMapping("/customerswithsalestime") // localhost:8080/customers/customerswithsalestime
    ResponseEntity<List<CustomerAndProductStatsDTO>> getCustomersAndTotalSalesTime();
    @GetMapping("/customersbetweendates") // localhost:8080/customers/customersbetweendates?fromDate=2023-02-02&toDate=2023-02-11
    ResponseEntity<List<CustomerAndProductStatsDTO>> getCustomersAndTotalSalesTimeBetweenDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
                                                                                               @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
    @GetMapping("/customerandtotalamountbetweendates") // localhost:8080/customers/customerandtotalamountbetweendates?customerId=19&fromDate=2023-02-02&toDate=2023-02-11
    ResponseEntity<CustomerAndProductStatsDTO> getCustomerAndTotalAmountBetweenDates(@RequestParam("customerId") Long customerId,
                                                                                     @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                                                     @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
    @GetMapping("/customerandtotalamountbeforedate/{customerId}") // localhost:8080/customers/customerandtotalamountbeforedate/20?beforeDate=2023-02-19
    ResponseEntity<CustomerAndProductStatsDTO> getCustomerAndTotalAmountBeforeDate(@PathVariable("customerId")Long customerId,
                                                                                   @RequestParam("beforeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate);
    @GetMapping("/customersbeforedate") // localhost:8080/customers/customersbeforedate?beforeDate=2023-01-11
    ResponseEntity<List<CustomerAndProductStatsDTO>> getCustomersAndTotalSalesTimeBeforeDate(@RequestParam("beforeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate);
}
