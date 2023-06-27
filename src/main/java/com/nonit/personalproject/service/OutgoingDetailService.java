package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.dto.customdto.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OutgoingDetailService {

    OutgoingDetailsCreateDTO findOutgoingDetailById(Long outgoingId);

    void deleteOutgoingDetail(Long outgoingId);

    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoing(@Param("date") LocalDateTime date);

    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount();

    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId);

    SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDateTime inputDate);

    List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates(LocalDateTime fromDate, LocalDateTime toDate);

    List<Object[]> getTop5Customers(String inputYear);

    List<PriceStatsDTO> getProductsTotalPrice();

    List<OutNoteStatsDTO> getOutAmountAndCustomer();

    List<MonthlyAmountDTO> getMonthLyOutgoingAmount();

    Object getOutAmountWithinMonth();
}
