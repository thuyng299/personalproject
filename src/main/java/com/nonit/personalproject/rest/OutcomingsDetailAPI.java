package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.dto.SalesTimeStatDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/outcomingsdetails")
public interface OutcomingsDetailAPI {
    @GetMapping("/product-outgoing") // localhost:8080/outcomingsdetails/product-outgoing?date=2023-01-21
    ResponseEntity<List<OutgoingAmountStatsDTO>> getNumberOfProductOutgoings(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
    @GetMapping("/outgoing-salestime-amount") // localhost:8080/outcomingsdetails/outgoing-salestime-amount
    ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmount();
    @GetMapping("/product-salestime-amount") // localhost:8080/outcomingsdetails/product-salestime-amount?inputId=15
    ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProduct(@RequestParam("inputId") Long inputId);
    @GetMapping("/inputproduct-salestime-amount") // localhost:8080/outcomingsdetails/inputproduct-salestime-amount?inputId=15&inputDate=2023-01-21
    ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(@RequestParam("inputId") Long inputId,
                                                                                           @RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);
}
