package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.dto.SalesTimeStatDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OutcomingsDetailService {
    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(@Param("date") LocalDate date);
    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount();
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId);
    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate (Long inputId, LocalDate inputDate);
    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates (LocalDate fromDate, LocalDate toDate);
}
