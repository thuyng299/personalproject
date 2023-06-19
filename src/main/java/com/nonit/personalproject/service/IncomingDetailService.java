package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IncomingDetailService {
    List<IncomingDetailDTO> getAllIncomingDetail();

    IncomingDetailDTO findIncomingDetailById(Long incomingId);

    void deleteIncomingDetail(Long incomingId);

    IncomingDetailDTO updateIncomingDetail(Long grnId, IncomingDetailUpdateDTO incomingDetailUpdateDTO);

    List<IncomingAmountStatsDTO> getNumberOfProductIncoming(LocalDateTime date);

    List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount();

    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(Long inputId);

    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDateTime inputDate);

    List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(LocalDateTime fromDate, LocalDateTime toDate);

    List<Object[]> getCountDaysAndAmountBeforeExpire(Long inputCountDays);

    List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock(Double inputAmount);

    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterial();

    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGood();

    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterialBeforeDate(LocalDateTime inputDate);

    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGoodBeforeDate(LocalDateTime inputDate);

    List<CostStatsDTO> getProductsTotalCost();

    List<IncomingProductStatDTO> getIncomingAmountOfProduct(Long inputProductId);

    TotalStockOfProductStatDTO getTotalStockAmountOfProduct(Long inputProductId);
}
