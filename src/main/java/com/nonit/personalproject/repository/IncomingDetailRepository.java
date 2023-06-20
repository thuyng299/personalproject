package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.customstatsdto.*;
import com.nonit.personalproject.entity.IncomingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncomingDetailRepository extends JpaRepository<IncomingDetail, Long> {

    List<IncomingDetail> findByProductIdOrderByExpirationDate(Long productId);

    @Query("select new com.nonit.personalproject.dto.customdto.IncomingAmountStatsDTO (id.product.id, p.name, sum(id.amount)) from GoodsReceivedNote grn, IncomingDetail id, Product p where grn.id = id.goodsReceivedNote.id and p.id = id.product.id and grn.incomingDate < :date group by id.product.id, p.name order by id.product.id")
    List<IncomingAmountStatsDTO> getNumberOfProductIncoming(@Param("date") LocalDateTime date);

    @Query("select new com.nonit.personalproject.dto.customdto.PurchaseTimeStatDTO (id.product.id, p.name, count(id.product.id), sum(id.amount)) from GoodsReceivedNote grn, IncomingDetail id, Product p where grn.id = id.goodsReceivedNote.id and p.id = id.product.id group by id.product.id, p.name order by count(id.product.id) desc")
    List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount();

    @Query("select new com.nonit.personalproject.dto.customdto.PurchaseTimeStatDTO (id.product.id, p.name, count(id.product.id), sum(id.amount)) from GoodsReceivedNote grn, IncomingDetail id, Product p where grn.id = id.goodsReceivedNote.id and p.id = id.product.id and id.product.id = :inputId group by id.product.id, p.name")
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(@Param("inputId") Long inputId);

    @Query("select new com.nonit.personalproject.dto.customdto.PurchaseTimeStatDTO (id.product.id, p.name, count(id.product.id), sum(id.amount)) from GoodsReceivedNote grn, IncomingDetail id, Product p where grn.id = id.goodsReceivedNote.id and p.id = id.product.id and id.product.id = :inputId and grn.incomingDate < :inputDate group by id.product.id, p.name")
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(@Param("inputId") Long inputId,
                                                                         @Param("inputDate") LocalDateTime inputDate);

    @Query("select new com.nonit.personalproject.dto.customdto.PurchaseTimeStatDTO (id.product.id, p.name, count(id.product.id), sum(id.amount)) from GoodsReceivedNote grn, IncomingDetail id, Product p where grn.id = id.goodsReceivedNote.id and p.id = id.product.id and grn.incomingDate between :fromDate and :toDate group by id.product.id, p.name order by id.product.id")
    List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(@Param("fromDate") LocalDateTime fromDate,
                                                                   @Param("toDate") LocalDateTime toDate);

    @Query(value = "select grn.grn_id, p.product_id, p.product_name, (id.expiration_date - current_date), sum(id.remaining_amount)from goods_received_note grn, incoming_detail id, product p where grn.grn_id = id.grn_id and p.product_id = id.product_id and (id.expiration_date - current_date) > 0 and (id.expiration_date - current_date) <= :inputCountDays group by grn.grn_id, p.product_id, p.product_name, id.expiration_date having sum(id.remaining_amount) > 0 order by (id.expiration_date - current_date)", nativeQuery = true)
    List<Object[]> getCountDaysAndAmountBeforeExpire (@Param("inputCountDays") Long inputCountDays);

    @Query("select new com.nonit.personalproject.dto.customdto.ProductNearlyOutOfStockStatDTO (p.id, p.name, sum(id.amount)) from IncomingDetail id, Product p where p.id = id.product.id group by p.id having sum(id.remainingAmount) < :inputAmount")
    List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock (@Param("inputAmount") Double inputAmount);

    @Query("select new com.nonit.personalproject.dto.customdto.StockAmountOfCategoryStatDTO (p.id, p.name, p.productCategory, sum(id.remainingAmount)) from IncomingDetail id, Product p where p.id = id.product.id group by p.productCategory, p.id having p.productCategory like '%RAW_MATERIALS%'")
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterial();

    @Query("select new com.nonit.personalproject.dto.customdto.StockAmountOfCategoryStatDTO (p.id, p.name, p.productCategory, sum(id.remainingAmount)) from IncomingDetail id, Product p where p.id = id.product.id group by p.productCategory, p.id having p.productCategory like '%FINISHED_GOODS%'")
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGood();

    @Query("select new com.nonit.personalproject.dto.customdto.StockAmountOfCategoryStatDTO (p.id, p.name, p.productCategory, sum(id.remainingAmount)) from GoodsReceivedNote grn, IncomingDetail id, Product p where grn.id = id.goodsReceivedNote.id and p.id = id.product.id and grn.incomingDate < :inputDate group by p.productCategory, p.id having p.productCategory like '%RAW_MATERIALS%'")
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterialBeforeDate(@Param("inputDate") LocalDateTime inputDate);

    @Query("select new com.nonit.personalproject.dto.customdto.StockAmountOfCategoryStatDTO (p.id, p.name, p.productCategory, sum(id.remainingAmount)) from GoodsReceivedNote grn, IncomingDetail id, Product p where grn.id = id.goodsReceivedNote.id and p.id = id.product.id and grn.incomingDate < :inputDate group by p.productCategory, p.id having p.productCategory like '%FINISHED_GOODS%'")
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGoodBeforeDate(@Param("inputDate") LocalDateTime inputDate);

    @Query("select new com.nonit.personalproject.dto.customdto.CostStatsDTO (id.product.id, p.name, sum(id.amount), sum(id.cost * id.amount)) from IncomingDetail id, Product p where p.id = id.product.id group by id.product.id, p.name order by sum(id.cost * id.amount) desc")
    List<CostStatsDTO> getProductsTotalCost();

    @Query("select new com.nonit.personalproject.dto.customdto.IncomingProductStatDTO (grn.id, grn.incomingDate, id.amount, id.remainingAmount, id.product.id) from IncomingDetail id join GoodsReceivedNote grn on grn.id = id.goodsReceivedNote.id and id.product.id = :inputProductId order by grn.incomingDate")
    List<IncomingProductStatDTO> getIncomingAmountOfProduct (@Param("inputProductId") Long inputProductId);

    @Query("select new com.nonit.personalproject.dto.customdto.TotalStockOfProductStatDTO (p.id, p.name, sum(id.remainingAmount)) from IncomingDetail id, Product p where p.id = id.product.id and id.product.id = :inputProductId group by p.id")
    TotalStockOfProductStatDTO getTotalStockAmountOfProduct(@Param("inputProductId") Long inputProductId);
}
