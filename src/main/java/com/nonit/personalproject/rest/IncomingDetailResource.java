package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.serviceimpl.IncomingDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IncomingDetailResource implements IncomingDetailAPI {
    private final IncomingDetailServiceImpl incomingDetailServiceImpl;

    @Override
    public ResponseEntity<List<IncomingDetailDTO>> getAllIncomingDetail() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getAllIncomingDetail());
    }

    @Override
    public ResponseEntity<IncomingDetailDTO> findIncomingDetailById(Long incomingId) {
        return ResponseEntity.ok(incomingDetailServiceImpl.findIncomingDetailById(incomingId));
    }

    @Override
    public ResponseEntity<Void> deleteIncomingDetail(Long incomingId) {
        incomingDetailServiceImpl.deleteIncomingDetail(incomingId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<IncomingDetailDTO> updateIncomingDetail(Long incomingId, IncomingDetailUpdateDTO incomingDetailUpdateDTO) {
        return ResponseEntity.ok().body(incomingDetailServiceImpl.updateIncomingDetail(incomingId, incomingDetailUpdateDTO));
    }
    @Override
    public ResponseEntity<List<IncomingAmountStatsDTO>> getNumberOfProductIncoming(LocalDate date) {
        LocalDateTime formattedDateTime = LocalDateTime.of(date, LocalTime.MAX);
        return ResponseEntity.ok(incomingDetailServiceImpl.getNumberOfProductIncoming(formattedDateTime));
    }

    @Override
    public ResponseEntity<List<PurchaseTimeStatDTO>> getNumberOfPurchaseTimeAndAmount() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getNumberOfPurchaseTimeAndAmount());
    }

    @Override
    public ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProduct(Long inputId) {
        return ResponseEntity.ok(incomingDetailServiceImpl.getPurchaseTimeAndAmountOfSpecificProduct(inputId));
    }

    @Override
    public ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
        LocalDateTime formattedDateTime = LocalDateTime.of(inputDate, LocalTime.MAX);
        return ResponseEntity.ok(incomingDetailServiceImpl.getPurchaseTimeAndAmountOfSpecificProductAndDate(inputId, formattedDateTime));
    }

    @Override
    public ResponseEntity<List<PurchaseTimeStatDTO>> getPurchaseTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.MAX);
        return ResponseEntity.ok(incomingDetailServiceImpl.getPurchaseTimeAndAmountBetweenDates(fromDateTime, toDateTime));
    }

    @Override
    public ResponseEntity<List<Object[]>> getCountDaysAndAmountBeforeExpire(Long inputCountDays) {
        return ResponseEntity.ok(incomingDetailServiceImpl.getCountDaysAndAmountBeforeExpire(inputCountDays));
    }

    @Override
    public ResponseEntity<List<ProductNearlyOutOfStockStatDTO>> getProductNearlyOutOfStock(Double inputAmount) {
        return ResponseEntity.ok(incomingDetailServiceImpl.getProductNearlyOutOfStock(inputAmount));
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterial() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getTotalStockAmountOfRawMaterial());
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGood() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getTotalStockAmountOfFinishedGood());
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterialBeforeDate(LocalDate inputDate) {
        LocalDateTime formattedDateTime = LocalDateTime.of(inputDate, LocalTime.MAX);
        return ResponseEntity.ok(incomingDetailServiceImpl.getTotalStockAmountOfRawMaterialBeforeDate(formattedDateTime));
    }

    @Override
    public ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGoodBeforeDate(LocalDate inputDate) {
        LocalDateTime formattedDateTime = LocalDateTime.of(inputDate, LocalTime.MAX);
        return ResponseEntity.ok(incomingDetailServiceImpl.getTotalStockAmountOfFinishedGoodBeforeDate(formattedDateTime));
    }

    @Override
    public ResponseEntity<List<CostStatsDTO>> getProductsTotalCost() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getProductsTotalCost());
    }


    @Override
    public ResponseEntity<List<IncomingProductStatDTO>> getIncomingAmountOfProduct(Long inputProductId) {
        return ResponseEntity.ok(incomingDetailServiceImpl.getIncomingAmountOfProduct(inputProductId));
    }

    @Override
    public ResponseEntity<TotalStockOfProductStatDTO> getTotalStockAmountOfProduct(Long inputProductId) {
        return ResponseEntity.ok(incomingDetailServiceImpl.getTotalStockAmountOfProduct(inputProductId));
    }

    @Override
    public ResponseEntity<Object> getMonthlyInAmount() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getMonthlyInAmount());
    }

    @Override
    public ResponseEntity<List<MonthlyAmountDTO>> getMonthLyIncomingAmount() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getMonthLyIncomingAmount());
    }

    @Override
    public ResponseEntity<List<YearAmountDTO>> getAnnualIncomingAmount() {
        return ResponseEntity.ok(incomingDetailServiceImpl.getAnnualIncomingAmount());
    }
}
