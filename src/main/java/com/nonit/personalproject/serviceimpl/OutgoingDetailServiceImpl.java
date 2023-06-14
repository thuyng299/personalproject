//package com.nonit.personalproject.serviceimpl;
//
//import com.nonit.personalproject.dto.*;
//import com.nonit.personalproject.entity.GoodsDeliveryNote;
//import com.nonit.personalproject.entity.OutgoingDetail;
//import com.nonit.personalproject.entity.Product;
//import com.nonit.personalproject.entity.WarehouseArea;
//import com.nonit.personalproject.exception.ResponseException;
//import com.nonit.personalproject.exception.WarehouseException;
//import com.nonit.personalproject.mapper.OutgoingDetailMapper;
//import com.nonit.personalproject.repository.*;
//import com.nonit.personalproject.service.OutgoingDetailService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class OutgoingDetailServiceImpl implements OutgoingDetailService {
//    private final OutcomingsDetailRepository outcomingsDetailRepository;
//    private final ProductRepository productRepository;
//    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
//    private final IncomingsDetailRepository incomingsDetailRepository;
//    private final WarehouseAreaRepository warehouseAreaRepository;
//    private final OutgoingDetailMapper outgoingDetailMapper = OutgoingDetailMapper.INSTANCE;
//
//    @Override
//    public List<OutgoingDetailDTO> getAllOutcomingsDetail() {
//        List<OutgoingDetail> outgoingDetails = outcomingsDetailRepository.findAll();
//        if (outgoingDetails.isEmpty()){
//            throw WarehouseException.OutcomingsDetailNotFound();
//        }
//        return outgoingDetailMapper.toDtos(outgoingDetails);
//    }
//
//    @Override
//    public OutgoingDetailDTO findOutcomingsDetailById(Long outcomingsId) {
//        OutgoingDetail outgoingDetail = outcomingsDetailRepository.findById(outcomingsId).orElseThrow(WarehouseException::OutcomingsDetailNotFound);
//        return outgoingDetailMapper.toDto(outgoingDetail);
//    }
//
////    @Override
////    public OutgoingDetailDTO createOutcomingsDetail(Long gdnId, OutgoingDetailCreateDTO outgoingDetailCreateDTO) {
////        GoodsDeliveryNote goodsDeliveryNote = goodsDeliveryNoteRepository.findById(gdnId).orElseThrow(WarehouseException::GDNNotFound);
////        WarehouseArea area = warehouseAreaRepository.findById(goodsDeliveryNote.getWarehouseArea().getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
////        Product product = productRepository.findByWarehouseAreaAreaId(area.getAreaId());
////        OutgoingDetail outgoingDetail = OutgoingDetail.builder()
//////                .outcomingsAmount(outcomingsDetailCreateDTO.getOutcomingsAmount())
////                .productPrice(outgoingDetailCreateDTO.getProductPrice())
////                .discount(outgoingDetailCreateDTO.getDiscount())
////                .goodsDeliveryNote(goodsDeliveryNote)
////                .product(product)
////                .build();
//        // get GDN where product id equals to outcomings detail's product id
////        List<IncomingsProductStatDTO> incomingsProductStatDTOList = incomingsDetailRepository.getIncomingsAmountOfProduct(product.getProductId()); // product outcomings detail
////        Double totalRemainingAmount = incomingsProductStatDTOList.stream()
////                .mapToDouble(IncomingsProductStatDTO::getRemainingAmount)
////                .sum();
////        if (outcomingsDetailCreateDTO.getOutcomingsAmount() > totalRemainingAmount){  // product outcomings detail
////            throw WarehouseException.badRequest("InvalidStock", "Remaining stock amount");
////        }else {
////            for (int i = 0; i < incomingsProductStatDTOList.size(); i++) {
////                if (outcomingsDetailCreateDTO.getOutcomingsAmount() < incomingsProductStatDTOList.get(i).getRemainingAmount()) {
////                    OutcomingsDetail outcomingsDetail = OutcomingsDetail.builder()
////                            .outcomingsAmount(outcomingsDetailCreateDTO.getOutcomingsAmount())
////                            .productPrice(outcomingsDetailCreateDTO.getProductPrice())
////                            .discount(outcomingsDetailCreateDTO.getDiscount())
////                            .goodsDeliveryNote(goodsDeliveryNote)
////                            .product(product)
////                            .build();
////                    incomingsProductStatDTOList.get(i).setRemainingAmount(incomingsProductStatDTOList.get(i).getRemainingAmount() - outcomingsDetailCreateDTO.getOutcomingsAmount());
////                    outcomingsDetailRepository.save(outcomingsDetail);
////                } else if (outcomingsDetailCreateDTO.getOutcomingsAmount() > incomingsProductStatDTOList.get(i).getRemainingAmount()) {
////                    OutcomingsDetail outcomingsDetail = OutcomingsDetail.builder()
////                            .outcomingsAmount(incomingsProductStatDTOList.get(i).getRemainingAmount())
////                            .productPrice(outcomingsDetailCreateDTO.getProductPrice())
////                            .discount(outcomingsDetailCreateDTO.getDiscount())
////                            .goodsDeliveryNote(goodsDeliveryNote)
////                            .product(product)
////                            .build();
////                    outcomingsDetailRepository.save(outcomingsDetail);
////                }
////            }
////        }
////        outgoingDetail = outcomingsDetailRepository.save(outgoingDetail);
////        return outgoingDetailMapper.toDto(outgoingDetail);
////    }
//
//    @Override
//    public OutgoingDetailDTO createOutcomingsDetail(Long gdnId, OutgoingDetailCreateDTO outgoingDetailCreateDTO, Long productId) {
////        GoodsDeliveryNote goodsDeliveryNote = goodsDeliveryNoteRepository.findById(gdnId).orElseThrow(WarehouseException::GDNNotFound);
////        Product product = productRepository.findById(productId).orElseThrow(WarehouseException::ProductNotFound);
////
//        OutgoingDetail outgoingDetail = OutgoingDetail.builder()
////                .outcomingsAmount(outgoingDetailCreateDTO.getOutcomingsAmount())
////                .productPrice(outgoingDetailCreateDTO.getProductPrice())
//                .discount(outgoingDetailCreateDTO.getDiscount())
////                .goodsDeliveryNote(goodsDeliveryNote)
//                .build();
////
////        if (goodsDeliveryNote.getWarehouseArea().getAreaId().equals(product.getWarehouseArea().getAreaId())){
////            outgoingDetail.setProduct(product);
////        }else {
////            WarehouseArea area = warehouseAreaRepository.findById(goodsDeliveryNote.getWarehouseArea().getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
////            product = productRepository.findByWarehouseAreaAreaId(area.getAreaId());
////            outgoingDetail.setProduct(product);
////        }
//        outgoingDetail = outcomingsDetailRepository.save(outgoingDetail);
//        return outgoingDetailMapper.toDto(outgoingDetail);
//    }
//
//    @Override
//    public void deleteIncomingsDetail(Long outcomingsId) {
//        log.info("delete outcomings detail by id {}", outcomingsId);
//        outcomingsDetailRepository.deleteById(outcomingsId);
//    }
//
//    @Override
//    public List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(LocalDate date) {
//        if (date.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
//        }
//        return outcomingsDetailRepository.getNumberOfProductOutgoings(date);
//    }
//
//    @Override
//    public List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmount() {
//        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmount();
//    }
//
//    @Override
//    public SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProduct(Long inputId) {
//        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
//        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmountOfSpecificProduct(inputId);
//    }
//
//    @Override
//    public SalesTimeStatDTO getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
//        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
//        if (inputDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
//        }
//        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmountOfSpecificProductAndDate(inputId, inputDate);
//    }
//
//    @Override
//    public List<SalesTimeStatDTO> getNumberOfSalesTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
//        if (fromDate.isAfter(LocalDate.now()) || toDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
//        }
//        return outcomingsDetailRepository.getNumberOfSalesTimeAndAmountBetweenDates(fromDate, toDate);
//    }
//
//    @Override
//    public List<Object[]> getTop5Customers(String inputYear) {
//        return outcomingsDetailRepository.getTop5Customers(inputYear);
//    }
//
//    @Override
//    public List<PriceStatsDTO> getProductsTotalPrice() {
//        return outcomingsDetailRepository.getProductsTotalPrice();
//    }
//}
