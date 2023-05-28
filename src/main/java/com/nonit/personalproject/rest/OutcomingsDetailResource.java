package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.dto.SalesTimeStatDTO;
import com.nonit.personalproject.serviceimpl.OutcomingsDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OutcomingsDetailResource implements OutcomingsDetailAPI{
    private final OutcomingsDetailServiceImpl outcomingsDetailServiceImpl;

    @Override
    public ResponseEntity<List<OutgoingAmountStatsDTO>> getNumberOfProductOutgoings(LocalDate date) {
        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfProductOutgoings(date));
    }

    @Override
    public ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmount() {
        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmount());
    }

    @Override
    public ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId) {
        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmountOfSpecificProduct(inputId));
    }

    @Override
    public ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(inputId, inputDate));
    }

    @Override
    public ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmountBetweenDates(fromDate, toDate));
    }

    @Override
    public ResponseEntity<List<Object[]>> getTop5Customers(String inputYear) {
        return ResponseEntity.ok(outcomingsDetailServiceImpl.getTop5Customers(inputYear));
    }
}
