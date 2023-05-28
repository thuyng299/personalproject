package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.OutcomingsDetailCreateDTO;
import com.nonit.personalproject.dto.OutcomingsDetailDTO;
import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.dto.SalesTimeStatDTO;
import com.nonit.personalproject.entity.GoodsDeliveryNote;
import com.nonit.personalproject.entity.OutcomingsDetail;
import com.nonit.personalproject.entity.Product;
import com.nonit.personalproject.exception.ResponseException;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.OutcomingsDetailMapper;
import com.nonit.personalproject.repository.GoodsDeliveryNoteRepository;
import com.nonit.personalproject.repository.IncomingsDetailRepository;
import com.nonit.personalproject.repository.OutcomingsDetailRepository;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.service.OutcomingsDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutcomingsDetailServiceImpl implements OutcomingsDetailService {
    private final OutcomingsDetailRepository outcomingsDetailRepository;
    private final ProductRepository productRepository;
    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
    private final IncomingsDetailRepository incomingsDetailRepository;
    private final OutcomingsDetailMapper outcomingsDetailMapper = OutcomingsDetailMapper.INSTANCE;

    @Override
    public List<OutcomingsDetailDTO> getAllOutcomingsDetail() {
        List<OutcomingsDetail> outcomingsDetails = outcomingsDetailRepository.findAll();
        if (outcomingsDetails.isEmpty()){
            throw WarehouseException.OutcomingsDetailNotFound();
        }
        return outcomingsDetailMapper.toDtos(outcomingsDetails);
    }

    @Override
    public OutcomingsDetailDTO findOutcomingsDetailById(Long outcomingsId) {
        OutcomingsDetail outcomingsDetail = outcomingsDetailRepository.findById(outcomingsId).orElseThrow(WarehouseException::OutcomingsDetailNotFound);
        return outcomingsDetailMapper.toDto(outcomingsDetail);
    }

    @Override
    public OutcomingsDetailDTO createOutcomingsDetail(Long gdnId, OutcomingsDetailCreateDTO outcomingsDetailCreateDTO) {
        return null;
    }

    @Override
    public OutcomingsDetailDTO createOutcomingsDetail(Long gdnId, OutcomingsDetailCreateDTO outcomingsDetailCreateDTO, Long productId) {
        return null;
    }

    @Override
    public void deleteIncomingsDetail(Long outcomingsId) {
        log.info("delete outcomings detail by id {}", outcomingsId);
        outcomingsDetailRepository.deleteById(outcomingsId);
    }

    @Override
    public List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(LocalDate date) {
        if (date.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        return outcomingsDetailRepository.getNumberOfProductOutgoings(date);
    }

    @Override
    public List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount() {
        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmount();
    }

    @Override
    public SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmountOfSpecificProduct(inputId);
    }

    @Override
    public SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        if (inputDate.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(inputId, inputDate);
    }

    @Override
    public List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
        if (fromDate.isAfter(LocalDate.now()) || toDate.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
        }
        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmountBetweenDates(fromDate, toDate);
    }

    @Override
    public List<Object[]> getTop5Customers(String inputYear) {
        return outcomingsDetailRepository.getTop5Customers(inputYear);
    }
}
