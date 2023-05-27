package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByCustomerCode(String customerCode);

    Boolean existsByCustomerName(String customerName);

    Boolean existsByCustomerEmail(String customerEmail);
}