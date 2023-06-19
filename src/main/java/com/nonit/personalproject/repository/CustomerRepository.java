package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.CustomerAndProductStatsDTO;
import com.nonit.personalproject.dto.CustomerStatsDTO;
import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import com.nonit.personalproject.entity.Customer;
import com.nonit.personalproject.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByCode(String customerCode);
    Boolean existsByName(String customerName);
    Boolean existsByEmail(String customerEmail);
    Boolean existsByPhone (String customerPhone);
    List<Customer> findByNameContainingIgnoreCase (String customerName);
    @Query("select new com.nonit.personalproject.dto.CustomerStatsDTO (c.id, c.name, p.name, sum(od.amount)) from Customer c, GoodsDeliveryNote gdn, OutgoingDetail od, Product p where c.id = gdn.customer.id and gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and lower(c.name) like lower(:inputName) group by c.id, c.name, p.name")
    List<CustomerStatsDTO> getCustomerAndItsProduct(@Param("inputName") String inputName);
    @Query("select new com.nonit.personalproject.dto.CustomerStatsDTO (c.id, c.name, p.name, sum(od.amount)) from Customer c, GoodsDeliveryNote gdn, OutgoingDetail od, Product p where c.id = gdn.customer.id and gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and lower(p.name) like lower(:inputProductName) group by c.id, c.name, p.name")
    List<CustomerStatsDTO> getProductAndItsCustomers (@Param("inputProductName") String inputProductName);
    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.id, c.name, count(c.id), sum(od.amount)) from Customer c, GoodsDeliveryNote gdn, OutgoingDetail od, Product p where c.id = gdn.customer.id and gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id group by c.id order by sum(od.amount) desc")
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTime();
    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.id, c.name, count(c.id), sum(od.amount)) from Customer c, GoodsDeliveryNote gdn, OutgoingDetail od, Product p where c.id = gdn.customer.id and gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and gdn.outgoingDate between :fromDate and :toDate group by c.id order by sum(od.amount) desc")
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBetweenDates (@Param("fromDate") LocalDateTime fromDate,
                                                                                @Param("toDate") LocalDateTime toDate);
    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.id, c.name, count(c.id), sum(od.amount)) from Customer c, GoodsDeliveryNote gdn, OutgoingDetail od, Product p where c.id = gdn.customer.id and gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and c.id = :customerId and gdn.outgoingDate between :fromDate and :toDate group by c.id order by sum(od.amount) desc")
    CustomerAndProductStatsDTO getCustomerAndTotalAmountBetweenDates (@Param("customerId") Long customerId,
                                                                        @Param("fromDate") LocalDateTime fromDate,
                                                                        @Param("toDate") LocalDateTime toDate);
    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.id, c.name, count(c.id), sum(od.amount)) from Customer c, GoodsDeliveryNote gdn, OutgoingDetail od, Product p where c.id = gdn.customer.id and gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and c.id = :customerId and gdn.outgoingDate < :beforeDate group by c.id order by sum(od.amount) desc")
    CustomerAndProductStatsDTO getCustomerAndTotalAmountBeforeDate (@Param("customerId") Long customerId,
                                                                      @Param("beforeDate") LocalDateTime beforeDate);
    @Query("select new com.nonit.personalproject.dto.CustomerAndProductStatsDTO (c.id, c.name, count(c.id), sum(od.amount)) from Customer c, GoodsDeliveryNote gdn, OutgoingDetail od, Product p where c.id = gdn.customer.id and gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and gdn.outgoingDate < :beforeDate group by c.id order by sum(od.amount) desc")
    List<CustomerAndProductStatsDTO> getCustomersAndTotalSalesTimeBeforeDate (@Param("beforeDate") LocalDateTime beforeDate);
}