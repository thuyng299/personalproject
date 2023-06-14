//package com.nonit.personalproject.rest;
//
//import com.nonit.personalproject.dto.*;
//import com.nonit.personalproject.serviceimpl.OutgoingDetailServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.net.URI;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequiredArgsConstructor
//public class OutcomingsDetailResource implements OutcomingsDetailAPI{
//    private final OutgoingDetailServiceImpl outcomingsDetailServiceImpl;
//
//    @Override
//    public ResponseEntity<OutgoingDetailDTO> createOutcomingsDetail(Long gdnId, OutgoingDetailCreateDTO outgoingDetailCreateDTO, Optional<Long> productId) {
//        OutgoingDetailDTO createdoutcomingsDetailDTO;
////        if (productId.isPresent()){
//            createdoutcomingsDetailDTO = outcomingsDetailServiceImpl.createOutcomingsDetail(gdnId, outgoingDetailCreateDTO, productId.get());
////        }else {
////            createdoutcomingsDetailDTO = outcomingsDetailServiceImpl.createOutcomingsDetail(gdnId, outgoingDetailCreateDTO);
////        }
//        return ResponseEntity.created(URI.create("/outcomingsdetails" + createdoutcomingsDetailDTO.getOutcomingsId())).body(createdoutcomingsDetailDTO);
//    }
//
//    @Override
//    public ResponseEntity<List<OutgoingAmountStatsDTO>> getNumberOfProductOutgoings(LocalDate date) {
//        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfProductOutgoings(date));
//    }
//
//    @Override
//    public ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmount() {
//        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmount());
//    }
//
//    @Override
//    public ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId) {
//        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmountOfSpecificProduct(inputId));
//    }
//
//    @Override
//    public ResponseEntity<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
//        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(inputId, inputDate));
//    }
//
//    @Override
//    public ResponseEntity<List<SalesTimeStatDTO>> getNumberOfSalesTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
//        return ResponseEntity.ok(outcomingsDetailServiceImpl.getNumberOfSalesTimeAndAmountBetweenDates(fromDate, toDate));
//    }
//
//    @Override
//    public ResponseEntity<List<Object[]>> getTop5Customers(String inputYear) {
//        return ResponseEntity.ok(outcomingsDetailServiceImpl.getTop5Customers(inputYear));
//    }
//
//    @Override
//    public ResponseEntity<List<PriceStatsDTO>> getProductsTotalPrice() {
//        return ResponseEntity.ok(outcomingsDetailServiceImpl.getProductsTotalPrice());
//    }
//}
