package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.IncomingsDetailCreateDTO;
import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
import com.nonit.personalproject.serviceimpl.IncomingsDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class IncomingsDetailResource implements IncomingsDetailAPI{
    private final IncomingsDetailServiceImpl incomingsDetailServiceImpl;

    @Override
    public ResponseEntity<List<IncomingsDetailDTO>> getAllIncomingsDetail() {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getAllIncomingsDetail());
    }

    @Override
    public ResponseEntity<IncomingsDetailDTO> findIncomingsDetailById(Long incomingsId) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.findIncomingsDetailById(incomingsId));
    }

    @Override
    public ResponseEntity<IncomingsDetailDTO> createIncomingsDetail(Long grnId, IncomingsDetailCreateDTO incomingsDetailCreateDTO, Optional<Long>productId) {
        IncomingsDetailDTO createdIncomingsDetailDTO;
        if(productId.isPresent()) {
            createdIncomingsDetailDTO = incomingsDetailServiceImpl.createIncomingsDetail(grnId, incomingsDetailCreateDTO, productId.get());
        } else {
            createdIncomingsDetailDTO = incomingsDetailServiceImpl.createIncomingsDetail(grnId, incomingsDetailCreateDTO);
        }
        return ResponseEntity.created(URI.create("/incomingsdetails" + createdIncomingsDetailDTO.getIncomingsId())).body(createdIncomingsDetailDTO);
    }

    @Override
    public ResponseEntity<Void> deleteIncomingsDetail(Long incomingsId) {
        incomingsDetailServiceImpl.deleteIncomingsDetail(incomingsId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<IncomingsAmountStatsDTO>> getNumberOfProductIncomings(LocalDate date) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getNumberOfProductIncomings(date));
    }
}
