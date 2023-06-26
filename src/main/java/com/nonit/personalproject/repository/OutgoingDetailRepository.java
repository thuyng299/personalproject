package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.customdto.*;
import com.nonit.personalproject.entity.OutgoingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutgoingDetailRepository extends JpaRepository<OutgoingDetail, Long> {
    @Query("select new com.nonit.personalproject.dto.customdto.OutgoingAmountStatsDTO (od.product.id, p.name, sum(od.amount)) from GoodsDeliveryNote gdn, OutgoingDetail od, Product p where gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and gdn.outgoingDate < :date group by od.product.id, p.name order by od.product.id")
    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoing(@Param("date") LocalDateTime date);

    @Query("select new com.nonit.personalproject.dto.customdto.SalesTimeStatDTO (od.product.id, p.name, count(od.product.id), sum(od.amount)) from GoodsDeliveryNote gdn, OutgoingDetail od, Product p where gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id group by od.product.id, p.name order by count(od.product.id) desc")
    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount();

    @Query("select new com.nonit.personalproject.dto.customdto.SalesTimeStatDTO (od.product.id, p.name, count(od.product.id), sum(od.amount)) from GoodsDeliveryNote gdn, OutgoingDetail od, Product p where gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and od.product.id = :inputId group by od.product.id, p.name")
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(@Param("inputId") Long inputId);

    @Query("select new com.nonit.personalproject.dto.customdto.SalesTimeStatDTO (od.product.id, p.name, count(od.product.id), sum(od.amount)) from GoodsDeliveryNote gdn, OutgoingDetail od, Product p where gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and od.product.id = :inputId and gdn.outgoingDate < :inputDate group by od.product.id, p.name")
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(@Param("inputId") Long inputId,
                                                                           @Param("inputDate") LocalDateTime inputDate);

    @Query("select new com.nonit.personalproject.dto.customdto.SalesTimeStatDTO (od.product.id, p.name, count(od.product.id), sum(od.amount)) from GoodsDeliveryNote gdn, OutgoingDetail od, Product p where gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and gdn.outgoingDate between :fromDate and :toDate group by od.product.id, p.name order by od.product.id")
    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates(@Param("fromDate") LocalDateTime fromDate,
                                                                     @Param("toDate") LocalDateTime toDate);

    @Query(value = "select c.customer_name, sum(od.outgoing_amount) from customer c , goods_delivery_note gdn , outgoing_detail od where c.customer_id = gdn.customer_id and gdn.gdn_id = od.gdn_id and to_char(gdn.outgoing_date, 'YYYY') = :inputYear group by c.customer_name, c.customer_id order by sum(od.outgoing_amount) desc limit 5", nativeQuery = true)
    List<Object[]> getTop5Customers(@Param("inputYear") String inputYear);

    @Query("select new com.nonit.personalproject.dto.customdto.PriceStatsDTO (od.product.id, p.name, sum(od.amount), sum(od.price * od.amount - od.price * od.amount * od.discount)) from OutgoingDetail od, Product p where p.id = od.product.id group by od.product.id, p.name order by sum(od.price * od.amount - od.price * od.amount * od.discount) desc")
    List<PriceStatsDTO> getProductsTotalPrice();

    @Query(value = "select SUM(od.outgoing_amount) from outgoing_detail od join goods_delivery_note gdn on od.gdn_id = gdn.gdn_id where TO_CHAR(CURRENT_TIMESTAMP, 'YYYY-MM') = TO_CHAR(gdn.outgoing_date , 'YYYY-MM')", nativeQuery = true)
    Object getOutAmountWithinMonth();

    @Query("select new com.nonit.personalproject.dto.customdto.OutNoteStatsDTO (gdn.code, c.name, c.email, gdn.outgoingDate, sum(od.amount)) from GoodsDeliveryNote gdn, OutgoingDetail od, Customer c where gdn.id = od.goodsDeliveryNote.id and gdn.customer.id = c.id group by gdn.code, c.name, c.email, gdn.outgoingDate order by gdn.outgoingDate desc")
    List<OutNoteStatsDTO> getOutAmountAndCustomer();

    @Query("select new com.nonit.personalproject.dto.customdto.MonthlyAmountDTO (p.code, sum(od.amount)) from Product p, OutgoingDetail od, GoodsDeliveryNote gdn where gdn.id = od.goodsDeliveryNote.id and p.id = od.product.id and function('TO_CHAR', CURRENT_TIMESTAMP, 'YYYY-MM') = function('TO_CHAR', gdn.outgoingDate, 'YYYY-MM') group by p.code")
    List<MonthlyAmountDTO> getMonthLyOutgoingAmount();
}
