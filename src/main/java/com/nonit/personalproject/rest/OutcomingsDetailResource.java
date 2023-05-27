package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
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
}
