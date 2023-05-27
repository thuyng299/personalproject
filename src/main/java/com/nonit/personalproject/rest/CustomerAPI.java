package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;
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
}
