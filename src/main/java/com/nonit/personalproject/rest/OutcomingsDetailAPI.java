package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/outcomingsdetails")
public interface OutcomingsDetailAPI {
    @GetMapping("/product-outgoing")
    ResponseEntity<List<OutgoingAmountStatsDTO>> getNumberOfProductOutgoings(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
