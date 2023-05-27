package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.IncomingsDetailCreateDTO;
import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
import com.nonit.personalproject.dto.PurchaseTimeStatDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/incomingsdetails")
public interface IncomingsDetailAPI {
    @GetMapping
    ResponseEntity<List<IncomingsDetailDTO>> getAllIncomingsDetail();
    @GetMapping("/{incomingsId}")
    ResponseEntity<IncomingsDetailDTO> findIncomingsDetailById(@PathVariable("incomingsId") Long incomingsId);
    @PostMapping("/{grnId}")
    ResponseEntity<IncomingsDetailDTO> createIncomingsDetail(@PathVariable("grnId") Long grnId,
                                                             @RequestBody IncomingsDetailCreateDTO incomingsDetailCreateDTO,
                                                             @RequestParam("productId") Optional<Long> productId);
    @DeleteMapping("/{incomingsId}")
    ResponseEntity<Void> deleteIncomingsDetail(@PathVariable("incomingsId") Long incomingsId);
    @GetMapping("/product-incoming") // localhost:8080/incomingsdetails/product-incoming?date=2024-02-27
    ResponseEntity<List<IncomingsAmountStatsDTO>> getNumberOfProductIncomings(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
    @GetMapping("/incoming-purchasetime-amount") // localhost:8080/incomingsdetails/incoming-purchasetime-amount
    ResponseEntity<List<PurchaseTimeStatDTO>> getNumberOfPurchaseTimeAndAmount();
    @GetMapping("/product-purchasetime-amount") // localhost:8080/incomingsdetails/product-purchasetime-amount?inputId=30
    ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProduct(@RequestParam("inputId") Long inputId);
    @GetMapping("/inputproduct-purchasetime-amount") // localhost:8080/incomingsdetails/inputproduct-purchasetime-amount?inputId=15&inputDate=2022-08-27
    ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProductAndDate(@RequestParam("inputId") Long inputId,
                                                                                         @RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);
    @GetMapping("/purchaseamountbetweendates") // localhost:8080/incomingsdetails/purchaseamountbetweendates?fromDate=2022-01-21&toDate=2022-07-21
    ResponseEntity<List<PurchaseTimeStatDTO>> getPurchaseTimeAndAmountBetweenDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
                                                                                   @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
}
