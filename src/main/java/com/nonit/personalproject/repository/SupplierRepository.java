package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import com.nonit.personalproject.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierName (String supplierName);
    Boolean existsBySupplierCode (String supplierCode);
    Boolean existsBySupplierName (String supplierName);
    Boolean existsBySupplierEmail (String supplierEmail);
    @Query("select new com.nonit.personalproject.dto.SupplierStatsDTO (s.supplierId, s.supplierName, p.productName, sum(id.incomingsAmount)) from Supplier s, GoodsReceivedNote grn, IncomingsDetail id, Product p where s.supplierId = grn.supplier.supplierId and grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and lower(s.supplierName) like lower(:inputName) group by s.supplierId, s.supplierName, p.productName")
    List<SupplierStatsDTO> getSupplierAndItsProduct(@Param("inputName") String inputName);
    @Query("select new com.nonit.personalproject.dto.SupplierStatsDTO (s.supplierId, s.supplierName, p.productName, sum(id.incomingsAmount)) from Supplier s, GoodsReceivedNote grn, IncomingsDetail id, Product p where s.supplierId = grn.supplier.supplierId and grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and lower(p.productName) like lower(:inputProductName) group by s.supplierId, s.supplierName, p.productName")
    List<SupplierStatsDTO> getProductAndItsSuppliers (@Param("inputProductName") String inputProductName);
    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.supplierId, s.supplierName, count(s.supplierId), sum(id.incomingsAmount)) from Supplier s, GoodsReceivedNote grn, IncomingsDetail id, Product p where s.supplierId = grn.supplier.supplierId and grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId group by s.supplierId order by s.supplierId")
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTime();
    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.supplierId, s.supplierName, count(s.supplierId), sum(id.incomingsAmount)) from Supplier s, GoodsReceivedNote grn, IncomingsDetail id, Product p where s.supplierId = grn.supplier.supplierId and grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and grn.incomingsDate between :fromDate and :toDate group by s.supplierId order by s.supplierId")
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBetweenDates(@Param("fromDate") LocalDate fromDate,
                                                                                  @Param("toDate") LocalDate toDate);
    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.supplierId, s.supplierName, count(s.supplierId), sum(id.incomingsAmount)) from Supplier s, GoodsReceivedNote grn, IncomingsDetail id, Product p where s.supplierId = grn.supplier.supplierId and grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and s.supplierId = :supplierId and grn.incomingsDate between :fromDate and :toDate group by s.supplierId order by s.supplierId")
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBetweenDates(@Param("supplierId") Long supplierId,
                                                                            @Param("fromDate") LocalDate fromDate,
                                                                           @Param("toDate") LocalDate toDate);
    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.supplierId, s.supplierName, count(s.supplierId), sum(id.incomingsAmount)) from Supplier s, GoodsReceivedNote grn, IncomingsDetail id, Product p where s.supplierId = grn.supplier.supplierId and grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and s.supplierId = :supplierId and grn.incomingsDate < :beforeDate group by s.supplierId order by s.supplierId")
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBeforeDate(@Param("supplierId") Long supplierId,
                                                                     @Param("beforeDate") LocalDate beforeDate);
    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.supplierId, s.supplierName, count(s.supplierId), sum(id.incomingsAmount)) from Supplier s, GoodsReceivedNote grn, IncomingsDetail id, Product p where s.supplierId = grn.supplier.supplierId and grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and grn.incomingsDate < :beforeDate group by s.supplierId order by s.supplierId")
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBeforeDate(@Param("beforeDate") LocalDate beforeDate);
}

