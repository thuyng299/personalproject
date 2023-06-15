package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OutgoingDetailService {
    List<OutgoingDetailDTO> getAllOutgoingDetail();
    OutgoingDetailDTO findOutgoingDetailById (Long outgoingId);
//    OutgoingDetailDTO createOutcomingsDetail (Long gdnId, OutgoingDetailCreateDTO outgoingDetailCreateDTO);
    OutgoingDetailDTO createOutcomingsDetail (Long gdnId, OutgoingDetailCreateDTO outgoingDetailCreateDTO, Long productId);
    void deleteOutgoingDetail (Long outgoingId);
//    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(@Param("date") LocalDate date);
//    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount();
//    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId);
//    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate (Long inputId, LocalDate inputDate);
//    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates (LocalDate fromDate, LocalDate toDate);
//    List<Object[]> getTop5Customers(String inputYear);
//    List<PriceStatsDTO> getProductsTotalPrice();
}
