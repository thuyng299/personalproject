package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IncomingsDetailService {
    List<IncomingsDetailDTO> getAllIncomingsDetail();
    IncomingsDetailDTO findIncomingsDetailById (Long incomingsId);
    IncomingsDetailDTO createIncomingsDetail (Long grnId, IncomingsDetailCreateDTO incomingsDetailCreateDTO);
    IncomingsDetailDTO createIncomingsDetail (Long grnId, IncomingsDetailCreateDTO incomingsDetailCreateDTO, Long productId);
    void deleteIncomingsDetail (Long incomingsId);
    List<IncomingsAmountStatsDTO> getNumberOfProductIncomings(LocalDate date);
    List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount();
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(Long inputId);
    PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate);
    List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate);
    List<Object[]> getCountDaysAndAmountBeforeExpire (Long inputCountDays);
    List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock (Double inputAmount);
}
