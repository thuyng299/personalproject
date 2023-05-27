package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.IncomingsDetailCreateDTO;
import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
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
    @GetMapping("/product-incoming")
    ResponseEntity<List<IncomingsAmountStatsDTO>> getNumberOfProductIncomings(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
