package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.CustomerStatsDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import com.nonit.personalproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByCustomerCode(String customerCode);

    Boolean existsByCustomerName(String customerName);

    Boolean existsByCustomerEmail(String customerEmail);
    @Query("select new com.nonit.personalproject.dto.CustomerStatsDTO (c.customerId, c.customerName, p.productName, sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and lower(c.customerName) like lower(:inputName) group by c.customerId, c.customerName, p.productName")
    List<CustomerStatsDTO> getCustomerAndItsProduct(@Param("inputName") String inputName);
    @Query("select new com.nonit.personalproject.dto.CustomerStatsDTO (c.customerId, c.customerName, p.productName, sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and lower(p.productName) like lower(:inputProductName) group by c.customerId, c.customerName, p.productName")
    List<CustomerStatsDTO> getProductAndItsCustomers (@Param("inputProductName") String inputProductName);

}