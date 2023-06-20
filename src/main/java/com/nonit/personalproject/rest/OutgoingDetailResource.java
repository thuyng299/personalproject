package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.customstatsdto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.dto.customstatsdto.PriceStatsDTO;
import com.nonit.personalproject.dto.customstatsdto.SalesTimeStatDTO;
import com.nonit.personalproject.serviceimpl.OutgoingDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OutgoingDetailResource implements OutgoingDetailAPI {
    private final OutgoingDetailServiceImpl outgoingDetailServiceImpl;

    @Override
    public ResponseEntity<List<OutgoingAmountStatsDTO>> getNumberOfProductOutgoing(LocalDate date) {
        LocalDateTime formattedDateTime = LocalDateTime.of(date, LocalTime.MAX);
        return ResponseEntity.ok(outgoingDetailServiceImpl.getNumberOfProductOutgoing(formattedDateTime));
    }

    @Override
    public ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmount() {
        return ResponseEntity.ok(outgoingDetailServiceImpl.getNumberOfSalesTimeAndAmount());
    }

    @Override
    public ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId) {
        return ResponseEntity.ok(outgoingDetailServiceImpl.getNumberOfSalesTimeAndAmountOfSpecificProduct(inputId));
    }

    @Override
    public ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
        LocalDateTime formattedDateTime = LocalDateTime.of(inputDate, LocalTime.MAX);
        return ResponseEntity.ok(outgoingDetailServiceImpl.getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(inputId, formattedDateTime));
    }

    @Override
    public ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.MAX);
        return ResponseEntity.ok(outgoingDetailServiceImpl.getNumberOfSalesTimeAndAmountBetweenDates(fromDateTime, toDateTime));
    }

    @Override
    public ResponseEntity<List<Object[]>> getTop5Customers(String inputYear) {
        return ResponseEntity.ok(outgoingDetailServiceImpl.getTop5Customers(inputYear));
    }

    @Override
    public ResponseEntity<List<PriceStatsDTO>> getProductsTotalPrice() {
        return ResponseEntity.ok(outgoingDetailServiceImpl.getProductsTotalPrice());
    }
}
