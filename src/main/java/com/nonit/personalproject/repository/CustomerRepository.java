package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.CustomerAndProductStatsDTO;
import com.nonit.personalproject.dto.CustomerStatsDTO;
import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import com.nonit.personalproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByCode(String customerCode);
    Boolean existsByName(String customerName);
    Boolean existsByEmail(String customerEmail);
    Boolean existsByPhone (String customerPhone);
//    @Query("select new com.nonit.personalproject.dto.CustomerStatsDTO (c.customerId, c.customerName, p.productName, sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and lower(c.customerName) like lower(:inputName) group by c.customerId, c.customerName, p.productName")
//    List<CustomerStatsDTO> getCustomerAndItsProduct(@Param("inputName") String inputName);
//    @Query("select new com.nonit.personalproject.dto.CustomerStatsDTO (c.customerId, c.customerName, p.productName, sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and lower(p.productName) like lower(:inputProductName) group by c.customerId, c.customerName, p.productName")
//    List<CustomerStatsDTO> getProductAndItsCustomers (@Param("inputProductName") String inputProductName);
//    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.customerId, c.customerName, count(c.customerId), sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId group by c.customerId order by sum(od.outcomingsAmount) desc")
//    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTime();
//    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.customerId, c.customerName, count(c.customerId), sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and gdn.outcomingsDate between :fromDate and :toDate group by c.customerId order by sum(od.outcomingsAmount) desc")
//    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBetweenDates (@Param("fromDate") LocalDate fromDate,
//                                                                                @Param("toDate") LocalDate toDate);
//    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.customerId, c.customerName, count(c.customerId), sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and c.customerId = :customerId and gdn.outcomingsDate between :fromDate and :toDate group by c.customerId order by sum(od.outcomingsAmount) desc")
//    CustomerAndProductStatsDTO getCustomerAndTotalAmountBetweenDates (@Param("customerId") Long customerId,
//                                                                        @Param("fromDate") LocalDate fromDate,
//                                                                        @Param("toDate") LocalDate toDate);
//    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.customerId, c.customerName, count(c.customerId), sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and c.customerId = :customerId and gdn.outcomingsDate < :beforeDate group by c.customerId order by sum(od.outcomingsAmount) desc")
//    CustomerAndProductStatsDTO getCustomerAndTotalAmountBeforeDate (@Param("customerId") Long customerId,
//                                                                      @Param("beforeDate") LocalDate beforeDate);
//    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.customerId, c.customerName, count(c.customerId), sum(od.outcomingsAmount)) from Customer c, GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where c.customerId = gdn.customer.customerId and gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and gdn.outcomingsDate < :beforeDate group by c.customerId order by sum(od.outcomingsAmount) desc")
//    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBeforeDate (@Param("beforeDate") LocalDate beforeDate);
}