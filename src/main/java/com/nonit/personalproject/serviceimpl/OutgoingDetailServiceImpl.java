package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.entity.OutgoingDetail;
import com.nonit.personalproject.entity.Product;
import com.nonit.personalproject.exception.ResponseException;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.OutgoingDetailMapper;
import com.nonit.personalproject.repository.*;
import com.nonit.personalproject.service.OutgoingDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutgoingDetailServiceImpl implements OutgoingDetailService {
    private final OutgoingDetailRepository outgoingDetailRepository;
    private final ProductRepository productRepository;
    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
    private final IncomingDetailRepository incomingDetailRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final OutgoingDetailMapper outgoingDetailMapper = OutgoingDetailMapper.INSTANCE;

    @Override
    public List<OutgoingDetailsCreateDTO> getAllOutgoingDetail() {
        List<OutgoingDetail> outgoingDetails = outgoingDetailRepository.findAll();
        if (outgoingDetails.isEmpty()){
            throw WarehouseException.OutgoingDetailNotFound();
        }
        return outgoingDetailMapper.toDtos(outgoingDetails);
    }

    @Override
    public OutgoingDetailsCreateDTO findOutgoingDetailById(Long outgoingId) {
        OutgoingDetail outgoingDetail = outgoingDetailRepository.findById(outgoingId).orElseThrow(WarehouseException::OutgoingDetailNotFound);
        return outgoingDetailMapper.toDto(outgoingDetail);
    }

    @Override
    public void deleteOutgoingDetail(Long outgoingId) {
        log.info("delete outcomings detail by id {}", outgoingId);
        outgoingDetailRepository.deleteById(outgoingId);
    }

    @Override
    public List<OutgoingAmountStatsDTO> getNumberOfProductOutgoing(LocalDateTime date) {
        if (date.isAfter(LocalDateTime.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDateTime.now());
        }
        return outgoingDetailRepository.getNumberOfProductOutgoing(date);
    }

    @Override
    public List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount() {
        return outgoingDetailRepository.getNumberOfSalesTimeAndAmount();
    }

    @Override
    public SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        return outgoingDetailRepository.getNumberOfSalesTimeAndAmountOfSpecificProduct(inputId);
    }

    @Override
    public SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDateTime inputDate) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        if (inputDate.isAfter(LocalDateTime.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDateTime.now());
        }
        return outgoingDetailRepository.getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(inputId, inputDate);
    }

    @Override
    public List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate.isAfter(LocalDateTime.now()) || toDate.isAfter(LocalDateTime.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDateTime.now());
        }
        return outgoingDetailRepository.getNumberOfSalesTimeAndAmountBetweenDates(fromDate, toDate);
    }

    @Override
    public List<Object[]> getTop5Customers(String inputYear) {
        return outgoingDetailRepository.getTop5Customers(inputYear);
    }

    @Override
    public List<PriceStatsDTO> getProductsTotalPrice() {
        return outgoingDetailRepository.getProductsTotalPrice();
    }
}
