package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();
    CustomerDTO findCustomerById (Long customerId);
    CustomerDTO createCustomer (Long countryId, CustomerCreateDTO customerCreateDTO);
    void deleteCustomer (Long customerId);
}
