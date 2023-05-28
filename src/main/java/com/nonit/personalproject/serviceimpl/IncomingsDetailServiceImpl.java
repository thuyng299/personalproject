package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.entity.*;
import com.nonit.personalproject.exception.ResponseException;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.IncomingsDetailMapper;
import com.nonit.personalproject.repository.GoodsReceivedNoteRepository;
import com.nonit.personalproject.repository.IncomingsDetailRepository;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.IncomingsDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomingsDetailServiceImpl implements IncomingsDetailService {
    private final IncomingsDetailRepository incomingsDetailRepository;
    private final ProductRepository productRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final GoodsReceivedNoteRepository goodsReceivedNoteRepository;
    private final IncomingsDetailMapper incomingsDetailMapper = IncomingsDetailMapper.INSTANCE;
    @Override
    public List<IncomingsDetailDTO> getAllIncomingsDetail() {
        List<IncomingsDetail> incomingsDetails = incomingsDetailRepository.findAll();
        if (incomingsDetails.isEmpty()){
            throw WarehouseException.IncomingsDetailNotFound();
        }
        return incomingsDetailMapper.toDtos(incomingsDetails);
    }

    @Override
    public IncomingsDetailDTO findIncomingsDetailById(Long incomingsId) {
        IncomingsDetail incomingsDetail = incomingsDetailRepository.findById(incomingsId).orElseThrow(WarehouseException::IncomingsDetailNotFound);
        return incomingsDetailMapper.toDto(incomingsDetail);
    }

    @Override
    public IncomingsDetailDTO createIncomingsDetail(Long grnId, IncomingsDetailCreateDTO incomingsDetailCreateDTO) {
        GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepository.findById(grnId).orElseThrow(WarehouseException::GRNNotFound);
        WarehouseArea area = warehouseAreaRepository.findById(goodsReceivedNote.getWarehouseArea().getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
        // Auto set product id based on GRN's area id (since 1 area is for only 1 product id)
        Product product = productRepository.findByWarehouseAreaAreaId(area.getAreaId());

        if (incomingsDetailCreateDTO.getIncomingsAmount() <= 0){
            throw WarehouseException.badRequest("InvalidIncomingsAmount", "Amount cannot be 0 or below 0!");
        }

        IncomingsDetail incomingsDetail = IncomingsDetail.builder()
                .incomingsAmount(incomingsDetailCreateDTO.getIncomingsAmount())
                .productCost(incomingsDetailCreateDTO.getProductCost())
                .remainingAmount(incomingsDetailCreateDTO.getIncomingsAmount())
                .goodsReceivedNote(goodsReceivedNote)
                .product(product)
                .build();
        // Auto set expiration date based on GRN date
        if (product.getProductCategory().equals(ProductCategory.RAW_MATERIALS)) {
            incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusYears(2L));
        } else if (product.getProductCategory().equals(ProductCategory.FINISHED_GOODS)) {
            incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusMonths(6L));
        }

        incomingsDetail = incomingsDetailRepository.save(incomingsDetail);
        return incomingsDetailMapper.toDto(incomingsDetail);
    }

    @Override
    public IncomingsDetailDTO createIncomingsDetail(Long grnId, IncomingsDetailCreateDTO incomingsDetailCreateDTO, Long productId) {
        GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepository.findById(grnId).orElseThrow(WarehouseException::GRNNotFound);
        Product product = productRepository.findById(productId).orElseThrow(WarehouseException::ProductNotFound);

        IncomingsDetail incomingsDetail = IncomingsDetail.builder()
                .incomingsAmount(incomingsDetailCreateDTO.getIncomingsAmount())
                .productCost(incomingsDetailCreateDTO.getProductCost())
                .goodsReceivedNote(goodsReceivedNote)
                .build();
        // Warehouse staff can input product id by themselves but if it's wrong, the system will force to be right by searching id on product table
        if (goodsReceivedNote.getWarehouseArea().getAreaId().equals(product.getWarehouseArea().getAreaId())) {
            incomingsDetail.setProduct(product);
            if (product.getProductCategory().equals(ProductCategory.RAW_MATERIALS)) {
                incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusYears(2L));
            } else if (product.getProductCategory().equals(ProductCategory.FINISHED_GOODS)) {
                incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusMonths(6L));
            }
        } else {
            WarehouseArea area = warehouseAreaRepository.findById(goodsReceivedNote.getWarehouseArea().getAreaId()).orElseThrow(WarehouseException::ProductNotFound);
            product = productRepository.findByWarehouseAreaAreaId(area.getAreaId());
            incomingsDetail.setProduct(product);
            if (product.getProductCategory().equals(ProductCategory.RAW_MATERIALS)) {
                incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusYears(2L));
            } else if (product.getProductCategory().equals(ProductCategory.FINISHED_GOODS)) {
                incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusMonths(6L));
            }
        }
        incomingsDetail = incomingsDetailRepository.save(incomingsDetail);
        return incomingsDetailMapper.toDto(incomingsDetail);
    }

    @Override
    public void deleteIncomingsDetail(Long incomingsId) {
        log.info("delete incomings detail by id {}", incomingsId);
        incomingsDetailRepository.deleteById(incomingsId);
    }
    // Incomings amount for each product before input date
    @Override
    public List<IncomingsAmountStatsDTO> getNumberOfProductIncomings(LocalDate date) {
        if (date.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        return incomingsDetailRepository.getNumberOfProductIncomings(date);
    }

    @Override
    public List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount() {
        return incomingsDetailRepository.getNumberOfPurchaseTimeAndAmount();
    }

    @Override
    public PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(Long inputId) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        return incomingsDetailRepository.getPurchaseTimeAndAmountOfSpecificProduct(inputId);
    }

    @Override
    public PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDate inputDate) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        if (inputDate.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        return incomingsDetailRepository.getPurchaseTimeAndAmountOfSpecificProductAndDate(inputId, inputDate);
    }

    @Override
    public List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(LocalDate fromDate, LocalDate toDate) {
        if (fromDate.isAfter(LocalDate.now()) || toDate.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDate.now());
        }
        return incomingsDetailRepository.getPurchaseTimeAndAmountBetweenDates(fromDate, toDate);
    }

    @Override
    public List<Object[]> getCountDaysAndAmountBeforeExpire(Long inputCountDays) {
        if (inputCountDays <= 0){
            throw WarehouseException.badRequest("InvalidCountDays", "Count days cannot be 0 or less than 0");
        }
        return incomingsDetailRepository.getCountDaysAndAmountBeforeExpire(inputCountDays);
    }

    @Override
    public List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock(Double inputAmount) {
        if (inputAmount < 500){
            throw WarehouseException.badRequest("InvalidAmount", "Warehouse stock amount of each product must be greater than 500 kgs");
        }
        return incomingsDetailRepository.getProductNearlyOutOfStock(inputAmount);
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterial() {
        return incomingsDetailRepository.getTotalStockAmountOfRawMaterial();
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGood() {
        return incomingsDetailRepository.getTotalStockAmountOfFinishedGood();
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterialBeforeDate(LocalDate inputDate) {
        if (inputDate.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        return incomingsDetailRepository.getTotalStockAmountOfRawMaterialBeforeDate(inputDate);
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGoodBeforeDate(LocalDate inputDate) {
        if (inputDate.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        return incomingsDetailRepository.getTotalStockAmountOfFinishedGoodBeforeDate(inputDate);
    }
}
