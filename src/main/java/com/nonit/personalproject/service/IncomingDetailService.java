package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface IncomingDetailService {
    List<IncomingsDetailDTO> getAllIncomingsDetail();
    IncomingsDetailDTO findIncomingsDetailById (Long incomingsId);
    void deleteIncomingsDetail (Long incomingsId);
    List<IncomingsAmountStatsDTO> getNumberOfProductIncomings(LocalDate date);
    List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount();
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(Long inputId);
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate);
    List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate);
    List<Object[]> getCountDaysAndAmountBeforeExpire (Long inputCountDays);
    List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock (Double inputAmount);
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterial();
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGood();
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterialBeforeDate(LocalDate inputDate);
    List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGoodBeforeDate(LocalDate inputDate);
    List<CostStatsDTO> getProductsTotalCost();
    IncomingsDetailDTO updateIncomingsDetail (Long grnId, IncomingsDetailUpdateDTO incomingsDetailUpdateDTO);
    List<IncomingsProductStatDTO> getIncomingsAmountOfProduct (Long inputProductId);
    TotalStockOfProductStatDTO getTotalStockAmountOfProduct(Long inputProductId);
}
