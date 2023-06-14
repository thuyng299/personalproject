//package com.nonit.personalproject.serviceimpl;
//
//import com.nonit.personalproject.dto.*;
//import com.nonit.personalproject.entity.*;
//import com.nonit.personalproject.exception.ResponseException;
//import com.nonit.personalproject.exception.WarehouseException;
//import com.nonit.personalproject.mapper.IncomingDetailMapper;
//import com.nonit.personalproject.repository.GoodsReceivedNoteRepository;
//import com.nonit.personalproject.repository.IncomingsDetailRepository;
//import com.nonit.personalproject.repository.ProductRepository;
//import com.nonit.personalproject.repository.WarehouseAreaRepository;
//import com.nonit.personalproject.service.IncomingDetailService;
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
//public class IncomingDetailServiceImpl implements IncomingDetailService {
//    private final IncomingsDetailRepository incomingsDetailRepository;
//    private final ProductRepository productRepository;
//    private final WarehouseAreaRepository warehouseAreaRepository;
//    private final GoodsReceivedNoteRepository goodsReceivedNoteRepository;
//    private final IncomingDetailMapper incomingDetailMapper = IncomingDetailMapper.INSTANCE;
//    @Override
//    public List<IncomingsDetailDTO> getAllIncomingsDetail() {
//        List<IncomingDetail> incomingDetails = incomingsDetailRepository.findAll();
//        if (incomingDetails.isEmpty()){
//            throw WarehouseException.IncomingsDetailNotFound();
//        }
//        return incomingDetailMapper.toDtos(incomingDetails);
//    }
//
//    @Override
//    public IncomingsDetailDTO findIncomingsDetailById(Long incomingsId) {
//        IncomingDetail incomingDetail = incomingsDetailRepository.findById(incomingsId).orElseThrow(WarehouseException::IncomingsDetailNotFound);
//        return incomingDetailMapper.toDto(incomingDetail);
//    }
//
//    @Override
//    public void deleteIncomingsDetail(Long incomingsId) {
//        log.info("delete incomings detail by id {}", incomingsId);
//        incomingsDetailRepository.deleteById(incomingsId);
//    }
//    // Incomings amount for each product before input date
//    @Override
//    public List<IncomingsAmountStatsDTO> getNumberOfProductIncomings(LocalDate date) {
//        if (date.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
//        }
//        return incomingsDetailRepository.getNumberOfProductIncomings(date);
//    }
//
//    @Override
//    public List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount() {
//        return incomingsDetailRepository.getNumberOfPurchaseTimeAndAmount();
//    }
//
//    @Override
//    public PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(Long inputId) {
//        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
//        return incomingsDetailRepository.getPurchaseTimeAndAmountOfSpecificProduct(inputId);
//    }
//
//    @Override
//    public PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
//        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
//        if (inputDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
//        }
//        return incomingsDetailRepository.getPurchaseTimeAndAmountOfSpecificProductAndDate(inputId, inputDate);
//    }
//
//    @Override
//    public List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
//        if (fromDate.isAfter(LocalDate.now()) || toDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
//        }
//        return incomingsDetailRepository.getPurchaseTimeAndAmountBetweenDates(fromDate, toDate);
//    }
//
//    @Override
//    public List<Object[]> getCountDaysAndAmountBeforeExpire(Long inputCountDays) {
//        if (inputCountDays <= 0){
//            throw WarehouseException.badRequest("InvalidCountDays", "Count days cannot be 0 or less than 0");
//        }
//        return incomingsDetailRepository.getCountDaysAndAmountBeforeExpire(inputCountDays);
//    }
//
//    @Override
//    public List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock(Double inputAmount) {
//        if (inputAmount < 500){
//            throw WarehouseException.badRequest("InvalidAmount", "Warehouse stock amount of each product must be greater than 500 kgs");
//        }
//        return incomingsDetailRepository.getProductNearlyOutOfStock(inputAmount);
//    }
//
//    @Override
//    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterial() {
//        return incomingsDetailRepository.getTotalStockAmountOfRawMaterial();
//    }
//
//    @Override
//    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGood() {
//        return incomingsDetailRepository.getTotalStockAmountOfFinishedGood();
//    }
//
//    @Override
//    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterialBeforeDate(LocalDate inputDate) {
//        if (inputDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
//        }
//        return incomingsDetailRepository.getTotalStockAmountOfRawMaterialBeforeDate(inputDate);
//    }
//
//    @Override
//    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGoodBeforeDate(LocalDate inputDate) {
//        if (inputDate.isAfter(LocalDate.now())){
//            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
//        }
//        return incomingsDetailRepository.getTotalStockAmountOfFinishedGoodBeforeDate(inputDate);
//    }
//
//    @Override
//    public List<CostStatsDTO> getProductsTotalCost() {
//        return incomingsDetailRepository.getProductsTotalCost();
//    }
//
//    @Override
//    public IncomingsDetailDTO updateIncomingsDetail(Long incomingsId, IncomingsDetailUpdateDTO incomingsDetailUpdateDTO) {
//        log.info("update incomings detail {}", incomingsId);
//        IncomingDetail incomingDetail = incomingsDetailRepository.findById(incomingsId).orElseThrow(WarehouseException::IncomingsDetailNotFound);
//        if (incomingsDetailUpdateDTO.getIncomingsAmount() <= 0){
//            throw WarehouseException.badRequest("InvalidIncomingsAmount", "Amount cannot be 0 or below 0!");
//        }
//        if (incomingsDetailUpdateDTO.getProductCost() < 0){
//            throw WarehouseException.badRequest("InvalidProductCost", "Product cost cannot below 0!");
//        }
//        incomingDetailMapper.mapFromDto(incomingsDetailUpdateDTO, incomingDetail);
//        return incomingDetailMapper.toDto(incomingsDetailRepository.save(incomingDetail));
//    }
//
//    @Override
//    public List<IncomingsProductStatDTO> getIncomingsAmountOfProduct(Long inputProductId) {
//        return incomingsDetailRepository.getIncomingsAmountOfProduct(inputProductId);
//    }
//
//    @Override
//    public TotalStockOfProductStatDTO getTotalStockAmountOfProduct(Long inputProductId) {
//        return incomingsDetailRepository.getTotalStockAmountOfProduct(inputProductId);
//    }
//}
