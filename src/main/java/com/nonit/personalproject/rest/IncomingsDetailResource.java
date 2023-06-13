package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.*;
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
        return ResponseEntity.ok(incomingsDetailServiceImpl.getPurchaseTimeAndAmountBetweenDates(fromDate, toDate));
    }

    @Override
    public ResponseEntity<List<Object[]>> getCountDaysAndAmountBeforeExpire(Long inputCountDays) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getCountDaysAndAmountBeforeExpire(inputCountDays));
    }

    @Override
    public ResponseEntity<List<ProductNearlyOutOfStockStatDTO>> getProductNearlyOutOfStock(Double inputAmount) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getProductNearlyOutOfStock(inputAmount));
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterial() {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getTotalStockAmountOfRawMaterial());
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGood() {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getTotalStockAmountOfFinishedGood());
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterialBeforeDate(LocalDate inputDate) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getTotalStockAmountOfRawMaterialBeforeDate(inputDate));
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGoodBeforeDate(LocalDate inputDate) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getTotalStockAmountOfFinishedGoodBeforeDate(inputDate));
    }

    @Override
    public ResponseEntity<List<CostStatsDTO>> getProductsTotalCost() {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getProductsTotalCost());
    }

    @Override
    public ResponseEntity<IncomingsDetailDTO> updateIncomingsDetail(Long incomingsId, IncomingsDetailUpdateDTO incomingsDetailUpdateDTO) {
        return ResponseEntity.ok().body(incomingsDetailServiceImpl.updateIncomingsDetail(incomingsId, incomingsDetailUpdateDTO));
    }

    @Override
    public ResponseEntity<List<IncomingsProductStatDTO>> getIncomingsAmountOfProduct(Long inputProductId) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getIncomingsAmountOfProduct(inputProductId));
    }

    @Override
    public ResponseEntity<TotalStockOfProductStatDTO> getTotalStockAmountOfProduct(Long inputProductId) {
        return ResponseEntity.ok(incomingsDetailServiceImpl.getTotalStockAmountOfProduct(inputProductId));
    }
}
