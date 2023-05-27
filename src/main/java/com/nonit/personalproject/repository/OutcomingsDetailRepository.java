package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.dto.PurchaseTimeStatDTO;
import com.nonit.personalproject.dto.SalesTimeStatDTO;
import com.nonit.personalproject.entity.OutcomingsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OutcomingsDetailRepository extends JpaRepository<OutcomingsDetail, Long> {
    @Query("select new com.nonit.personalproject.dto.OutgoingAmountStatsDTO (od.product.productId, p.productName, sum(od.outcomingsAmount)) from GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and gdn.outcomingsDate < :date group by od.product.productId, p.productName order by od.product.productId")
    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(@Param("date") LocalDate date);
    @Query("select new com.nonit.personalproject.dto.SalesTimeStatDTO (od.product.productId, p.productName, count(od.product.productId), sum(od.outcomingsAmount)) from GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId group by od.product.productId, p.productName order by count(od.product.productId) desc")
    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount();
    @Query("select new com.nonit.personalproject.dto.SalesTimeStatDTO (od.product.productId, p.productName, count(od.product.productId), sum(od.outcomingsAmount)) from GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and od.product.productId = :inputId group by od.product.productId, p.productName")
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(@Param("inputId") Long inputId);
    @Query("select new com.nonit.personalproject.dto.SalesTimeStatDTO (od.product.productId, p.productName, count(od.product.productId), sum(od.outcomingsAmount)) from GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and od.product.productId = :inputId and gdn.outcomingsDate < :inputDate group by od.product.productId, p.productName")
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate (@Param("inputId") Long inputId,
                                                                            @Param("inputDate") LocalDate inputDate);
}
