package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.dto.CustomerStatsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
