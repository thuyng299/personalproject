package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.customdto.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/outgoingdetails")
public interface OutgoingDetailAPI {

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/product-outgoing")
        // localhost:8080/outgoingdetails/product-outgoing?date=2023-01-21
    ResponseEntity<List<OutgoingAmountStatsDTO>> getNumberOfProductOutgoing(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/outgoing-salestime-amount")
        // localhost:8080/outgoingdetails/outgoing-salestime-amount
    ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmount();

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/product-salestime-amount")
        // localhost:8080/outgoingdetails/product-salestime-amount?inputId=15
    ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProduct(@RequestParam("inputId") Long inputId);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/inputproduct-salestime-amount")
        // localhost:8080/outgoingdetails/inputproduct-salestime-amount?inputId=15&inputDate=2023-01-21
    ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(@RequestParam("inputId") Long inputId,
                                                                                           @RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/salesamount-fromdate-todate")
        // localhost:8080/outgoingdetails/salesamountbetweendates?fromDate=2023-01-03&toDate=2023-01-19
    ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmountBetweenDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                                                     @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/top5-customers")
        // localhost:8080/outgoingdetails/top5customers?inputYear=2023
    ResponseEntity<List<Object[]>> getTop5Customers(@RequestParam("inputYear") String inputYear);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/totalproductprice")
        // localhost:8080/outgoingdetails/totalproductprice
    ResponseEntity<List<PriceStatsDTO>> getProductsTotalPrice();

    @GetMapping("/outnote-customer")
        // localhost:8080/outgoingdetails/outnote-customer
    ResponseEntity<List<OutNoteStatsDTO>> getOutAmountAndCustomer();

    //    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/total-outgoing-within-month")  // localhost:8080/outgoingdetails/total-outgoing-within-month
    ResponseEntity<Object> getOutAmountWithinMonth();

    //    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/total-monthly-outgoing")
    // localhost:8080/outgoingdetails/total-monthly-outgoing
    ResponseEntity<List<MonthlyAmountDTO>> getMonthLyOutgoingAmount();
}
