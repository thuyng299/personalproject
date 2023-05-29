package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.entity.GoodsDeliveryNote;
import com.nonit.personalproject.entity.OutcomingsDetail;
import com.nonit.personalproject.entity.Product;
import com.nonit.personalproject.entity.WarehouseArea;
import com.nonit.personalproject.exception.ResponseException;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.OutcomingsDetailMapper;
import com.nonit.personalproject.repository.*;
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
    private final WarehouseAreaRepository warehouseAreaRepository;
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
        GoodsDeliveryNote goodsDeliveryNote = goodsDeliveryNoteRepository.findById(gdnId).orElseThrow(WarehouseException::GDNNotFound);
        WarehouseArea area = warehouseAreaRepository.findById(goodsDeliveryNote.getWarehouseArea().getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
        Product product = productRepository.findByWarehouseAreaAreaId(area.getAreaId());
//        OutcomingsDetail outcomingsDetail = OutcomingsDetail.builder()
////                .outcomingsAmount(outcomingsDetailCreateDTO.getOutcomingsAmount())
//                .productPrice(outcomingsDetailCreateDTO.getProductPrice())
//                .discount(outcomingsDetailCreateDTO.getDiscount())
//                .goodsDeliveryNote(goodsDeliveryNote)
//                .product(product)
//                .build();
        // get GDN where product id equals to outcomings detail's product id
        List<IncomingsProductStatDTO> incomingsProductStatDTOList = incomingsDetailRepository.getIncomingsAmountOfProduct(product.getProductId()); // product outcomings detail
        Double totalRemainingAmount = incomingsProductStatDTOList.stream()
                .mapToDouble(IncomingsProductStatDTO::getRemainingAmount)
                .sum();
        if (outcomingsDetailCreateDTO.getOutcomingsAmount() > totalRemainingAmount){  // product outcomings detail
            throw WarehouseException.badRequest("InvalidStock", "Remaining stock amount");
        }else {
            for (int i = 0; i < incomingsProductStatDTOList.size(); i++) {
                if (outcomingsDetailCreateDTO.getOutcomingsAmount() < incomingsProductStatDTOList.get(i).getRemainingAmount()) {
                    OutcomingsDetail outcomingsDetail = OutcomingsDetail.builder()
                            .outcomingsAmount(outcomingsDetailCreateDTO.getOutcomingsAmount())
                            .productPrice(outcomingsDetailCreateDTO.getProductPrice())
                            .discount(outcomingsDetailCreateDTO.getDiscount())
                            .goodsDeliveryNote(goodsDeliveryNote)
                            .product(product)
                            .build();
                    incomingsProductStatDTOList.get(i).setRemainingAmount(incomingsProductStatDTOList.get(i).getRemainingAmount() - outcomingsDetailCreateDTO.getOutcomingsAmount());
                    outcomingsDetailRepository.save(outcomingsDetail);
                } else if (outcomingsDetailCreateDTO.getOutcomingsAmount() > incomingsProductStatDTOList.get(i).getRemainingAmount()) {
                    OutcomingsDetail outcomingsDetail = OutcomingsDetail.builder()
                            .outcomingsAmount(incomingsProductStatDTOList.get(i).getRemainingAmount())
                            .productPrice(outcomingsDetailCreateDTO.getProductPrice())
                            .discount(outcomingsDetailCreateDTO.getDiscount())
                            .goodsDeliveryNote(goodsDeliveryNote)
                            .product(product)
                            .build();
                    outcomingsDetailRepository.save(outcomingsDetail);
                }
            }
        }
        return outcomingsDetailMapper.toDtos();
    }

    @Override
    public OutcomingsDetailDTO createOutcomingsDetail(Long gdnId, OutcomingsDetailCreateDTO outcomingsDetailCreateDTO, Long productId) {
        GoodsDeliveryNote goodsDeliveryNote = goodsDeliveryNoteRepository.findById(gdnId).orElseThrow(WarehouseException::GDNNotFound);
        Product product = productRepository.findById(productId).orElseThrow(WarehouseException::ProductNotFound);

        OutcomingsDetail outcomingsDetail = OutcomingsDetail.builder()
                .outcomingsAmount(outcomingsDetailCreateDTO.getOutcomingsAmount())
                .productPrice(outcomingsDetailCreateDTO.getProductPrice())
                .discount(outcomingsDetailCreateDTO.getDiscount())
                .goodsDeliveryNote(goodsDeliveryNote)
                .build();

        if (goodsDeliveryNote.getWarehouseArea().getAreaId().equals(product.getWarehouseArea().getAreaId())){
            outcomingsDetail.setProduct(product);
        }else {
            WarehouseArea area = warehouseAreaRepository.findById(goodsDeliveryNote.getWarehouseArea().getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
            product = productRepository.findByWarehouseAreaAreaId(area.getAreaId());
            outcomingsDetail.setProduct(product);
        }
        outcomingsDetail = outcomingsDetailRepository.save(outcomingsDetail);
        return outcomingsDetailMapper.toDto(outcomingsDetail);
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

    @Override
    public List<PriceStatsDTO> getProductsTotalPrice() {
        return outcomingsDetailRepository.getProductsTotalPrice();
    }
}
