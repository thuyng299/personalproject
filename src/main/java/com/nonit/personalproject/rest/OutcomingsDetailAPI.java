package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/outcomingsdetails")
public interface OutcomingsDetailAPI {
    @PostMapping("/{gdnId}")
    ResponseEntity<OutcomingsDetailDTO> createOutcomingsDetail(@PathVariable("gdnId") Long gdnId,
                                                               @RequestBody OutcomingsDetailCreateDTO outcomingsDetailCreateDTO,
                                                               @RequestParam("productId") Optional<Long> productId);
    @GetMapping("/product-outgoing") // localhost:8080/outcomingsdetails/product-outgoing?date=2023-01-21
    ResponseEntity<List<OutgoingAmountStatsDTO>> getNumberOfProductOutgoings(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
    @GetMapping("/outgoing-salestime-amount") // localhost:8080/outcomingsdetails/outgoing-salestime-amount
    ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmount();
    @GetMapping("/product-salestime-amount") // localhost:8080/outcomingsdetails/product-salestime-amount?inputId=15
    ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProduct(@RequestParam("inputId") Long inputId);
    @GetMapping("/inputproduct-salestime-amount") // localhost:8080/outcomingsdetails/inputproduct-salestime-amount?inputId=15&inputDate=2023-01-21
    ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(@RequestParam("inputId") Long inputId,
                                                                                           @RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);
    @GetMapping("/salesamountbetweendates") // localhost:8080/outcomingsdetails/salesamountbetweendates?fromDate=2023-01-03&toDate=2023-01-19
    ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmountBetweenDates (@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
                                                                                      @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
    @GetMapping("/top5customers") // localhost:8080/outcomingsdetails/top5customers?inputYear=2023
    ResponseEntity<List<Object[]>> getTop5Customers(@RequestParam("inputYear") String inputYear);
    @GetMapping("/totalproductprice") // localhost:8080/outcomingsdetails/totalproductprice
    ResponseEntity<List<PriceStatsDTO>> getProductsTotalPrice();
}
