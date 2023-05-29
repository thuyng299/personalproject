package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OutcomingsDetailService {
    List<OutcomingsDetailDTO> getAllOutcomingsDetail();
    OutcomingsDetailDTO findOutcomingsDetailById (Long outcomingsId);
    OutcomingsDetailDTO createOutcomingsDetail (Long gdnId, OutcomingsDetailCreateDTO outcomingsDetailCreateDTO);
    OutcomingsDetailDTO createOutcomingsDetail (Long gdnId, OutcomingsDetailCreateDTO outcomingsDetailCreateDTO, Long productId);
    void deleteIncomingsDetail (Long outcomingsId);
    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(@Param("date") LocalDate date);
    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount();
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId);
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate (Long inputId, LocalDate inputDate);
    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates (LocalDate fromDate, LocalDate toDate);
    List<Object[]> getTop5Customers(String inputYear);
    List<PriceStatsDTO> getProductsTotalPrice();
}
