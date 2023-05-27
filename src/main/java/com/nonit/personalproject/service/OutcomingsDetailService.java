package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OutcomingsDetailService {
    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(@Param("date") LocalDate date);
}
