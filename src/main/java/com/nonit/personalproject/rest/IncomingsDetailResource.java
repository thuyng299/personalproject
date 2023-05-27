package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.IncomingsDetailCreateDTO;
import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
import com.nonit.personalproject.dto.PurchaseTimeStatDTO;
import com.nonit.personalproject.exception.NullInputProductIdException;
import com.nonit.personalproject.exception.ResponseException;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.serviceimpl.IncomingsDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Override
    public ResponseEntity<List<PurchaseTimeStatDTO>> getNumberOfPurchaseTimeAndAmount() {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getNumberOfPurchaseTimeAndAmount());
    }

    @Override
    public ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProduct(Long inputId) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getPurchaseTimeAndAmountOfSpecificProduct(inputId));
    }

    @Override
    public ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getPurchaseTimeAndAmountOfSpecificProductAndDate(inputId, inputDate));
    }

    @Override
    public ResponseEntity<List<PurchaseTimeStatDTO>> getPurchaseTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
        if (fromDate == null || toDate == null){
            throw new IllegalArgumentException("Invalid date! Date cannot be null.");
        }
        return ResponseEntity.ok(incomingsDetailServiceImpl.getPurchaseTimeAndAmountBetweenDates(fromDate, toDate));
    }
}
