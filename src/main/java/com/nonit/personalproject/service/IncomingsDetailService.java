package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.IncomingsDetailCreateDTO;
import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;

import java.time.LocalDate;
import java.util.List;

public interface IncomingsDetailService {
    List<IncomingsDetailDTO> getAllIncomingsDetail();
    IncomingsDetailDTO findIncomingsDetailById (Long incomingsId);
    IncomingsDetailDTO createIncomingsDetail (Long grnId, IncomingsDetailCreateDTO incomingsDetailCreateDTO);
    IncomingsDetailDTO createIncomingsDetail (Long grnId, IncomingsDetailCreateDTO incomingsDetailCreateDTO, Long productId);
    void deleteIncomingsDetail (Long incomingsId);
    List<IncomingsAmountStatsDTO> getNumberOfProductIncomings(LocalDate date);
}
