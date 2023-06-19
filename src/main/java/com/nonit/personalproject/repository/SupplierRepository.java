package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.SupplierAndProductStatsDTO;
import com.nonit.personalproject.dto.SupplierStatsDTO;
import com.nonit.personalproject.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameIgnoreCaseContaining (String supplierName);
    Boolean existsByCode (String supplierCode);
    Boolean existsByName (String supplierName);
    Boolean existsByEmail (String supplierEmail);
    Boolean existsByPhone (String supplierPhone);
    @Query("select new com.nonit.personalproject.dto.SupplierStatsDTO (s.id, s.name, p.name, sum(id.amount)) from Supplier s, GoodsReceivedNote grn, IncomingDetail id, Product p where s.id = grn.supplier.id and grn.id = id.goodsReceivedNote.id and p.id = id.product.id and lower(s.name) like lower(:inputName) group by s.id, s.name, p.name")
    List<SupplierStatsDTO> getSupplierAndItsProduct(@Param("inputName") String inputName);

    @Query("select new com.nonit.personalproject.dto.SupplierStatsDTO (s.id, s.name, p.name, sum(id.amount)) from Supplier s, GoodsReceivedNote grn, IncomingDetail id, Product p where s.id = grn.supplier.id and grn.id = id.goodsReceivedNote.id and p.id = id.product.id and lower(p.name) like lower(:inputProductName) group by s.id, s.name, p.name")
    List<SupplierStatsDTO> getProductAndItsSuppliers (@Param("inputProductName") String inputProductName);

    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.id, s.name, count(s.id), sum(id.amount)) from Supplier s, GoodsReceivedNote grn, IncomingDetail id, Product p where s.id = grn.supplier.id and grn.id = id.goodsReceivedNote.id and p.id = id.product.id group by s.id order by s.id")
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTime();

    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.id, s.name, count(s.id), sum(id.amount)) from Supplier s, GoodsReceivedNote grn, IncomingDetail id, Product p where s.id = grn.supplier.id and grn.id = id.goodsReceivedNote.id and p.id = id.product.id and grn.incomingDate between :fromDate and :toDate group by s.id order by s.id")
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBetweenDates(@Param("fromDate") LocalDateTime fromDate,
                                                                                  @Param("toDate") LocalDateTime toDate);

    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.id, s.name, count(s.id), sum(id.amount)) from Supplier s, GoodsReceivedNote grn, IncomingDetail id, Product p where s.id = grn.supplier.id and grn.id = id.goodsReceivedNote.id and p.id = id.product.id and s.id = :supplierId and grn.incomingDate between :fromDate and :toDate group by s.id order by s.id")
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBetweenDates(@Param("supplierId") Long supplierId,
                                                                            @Param("fromDate") LocalDateTime fromDate,
                                                                           @Param("toDate") LocalDateTime toDate);

    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.id, s.name, count(s.id), sum(id.amount)) from Supplier s, GoodsReceivedNote grn, IncomingDetail id, Product p where s.id = grn.supplier.id and grn.id = id.goodsReceivedNote.id and p.id = id.product.id and s.id = :supplierId and grn.incomingDate < :beforeDate group by s.id order by s.id")
    SupplierAndProductStatsDTO getSupplierAndTotalAmountBeforeDate(@Param("supplierId") Long supplierId,
                                                                     @Param("beforeDate") LocalDateTime beforeDate);

    @Query("select new com.nonit.personalproject.dto.SupplierAndProductStatsDTO (s.id, s.name, count(s.id), sum(id.amount)) from Supplier s, GoodsReceivedNote grn, IncomingDetail id, Product p where s.id = grn.supplier.id and grn.id = id.goodsReceivedNote.id and p.id = id.product.id and grn.incomingDate < :beforeDate group by s.id order by s.id")
    List<SupplierAndProductStatsDTO> getSuppliersAndTotalPurchaseTimeBeforeDate(@Param("beforeDate") LocalDateTime beforeDate);
}

