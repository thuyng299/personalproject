package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.ExpiryDateAndAmountStatDTO;
import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
import com.nonit.personalproject.dto.ProductNearlyOutOfStockStatDTO;
import com.nonit.personalproject.dto.PurchaseTimeStatDTO;
import com.nonit.personalproject.entity.IncomingsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomingsDetailRepository extends JpaRepository<IncomingsDetail, Long> {
    @Query("select new com.nonit.personalproject.dto.IncomingsAmountStatsDTO (id.product.productId, p.productName, sum(id.incomingsAmount)) from GoodsReceivedNote grn, IncomingsDetail id, Product p where grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and grn.incomingsDate < :date group by id.product.productId, p.productName order by id.product.productId")
    List<IncomingsAmountStatsDTO> getNumberOfProductIncomings(@Param("date") LocalDate date);
    @Query("select new com.nonit.personalproject.dto.PurchaseTimeStatDTO (id.product.productId, p.productName, count(id.product.productId), sum(id.incomingsAmount)) from GoodsReceivedNote grn, IncomingsDetail id, Product p where grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId group by id.product.productId, p.productName order by count(id.product.productId) desc")
    List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount();
    @Query("select new com.nonit.personalproject.dto.PurchaseTimeStatDTO (id.product.productId, p.productName, count(id.product.productId), sum(id.incomingsAmount)) from GoodsReceivedNote grn, IncomingsDetail id, Product p where grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and id.product.productId = :inputId group by id.product.productId, p.productName")
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(@Param("inputId") Long inputId);
    @Query("select new com.nonit.personalproject.dto.PurchaseTimeStatDTO (id.product.productId, p.productName, count(id.product.productId), sum(id.incomingsAmount)) from GoodsReceivedNote grn, IncomingsDetail id, Product p where grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and id.product.productId = :inputId and grn.incomingsDate < :inputDate group by id.product.productId, p.productName")
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(@Param("inputId") Long inputId,
                                                                         @Param("inputDate") LocalDate inputDate);
    @Query("select new com.nonit.personalproject.dto.PurchaseTimeStatDTO (id.product.productId, p.productName, count(id.product.productId), sum(id.incomingsAmount)) from GoodsReceivedNote grn, IncomingsDetail id, Product p where grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and grn.incomingsDate between :fromDate and :toDate group by id.product.productId, p.productName order by id.product.productId")
    List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(@Param("fromDate") LocalDate fromDate,
                                                             @Param("toDate") LocalDate toDate);
    @Query(value = "select grn.grn_id, p.product_id, p.product_name, (id.expiration_date - current_date), sum(id.remaining_amount)from goods_received_note grn, incomings_detail id, product p where grn.grn_id = id.grn_id and p.product_id = id.product_id and (id.expiration_date - current_date) > 0 and (id.expiration_date - current_date) <= :inputCountDays group by grn.grn_id, p.product_id, p.product_name, id.expiration_date having sum(id.remaining_amount) > 0 order by (id.expiration_date - current_date)", nativeQuery = true)
    List<Object[]> getCountDaysAndAmountBeforeExpire (@Param("inputCountDays") Long inputCountDays);
    @Query("select new com.nonit.personalproject.dto.ProductNearlyOutOfStockStatDTO (p.productId, p.productName, sum(id.incomingsAmount)) from IncomingsDetail id, Product p where p.productId = id.product.productId group by p.productId having sum(id.remainingAmount) < :inputAmount")
    List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock (@Param("inputAmount") Double inputAmount);
}
