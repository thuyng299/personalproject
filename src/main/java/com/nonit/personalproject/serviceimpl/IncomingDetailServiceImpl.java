package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.dto.customdto.*;
import com.nonit.personalproject.entity.*;
import com.nonit.personalproject.exception.ResponseException;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.IncomingDetailMapper;
import com.nonit.personalproject.repository.GoodsReceivedNoteRepository;
import com.nonit.personalproject.repository.IncomingDetailRepository;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.IncomingDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomingDetailServiceImpl implements IncomingDetailService {
    private final IncomingDetailRepository incomingDetailRepository;
    private final ProductRepository productRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final GoodsReceivedNoteRepository goodsReceivedNoteRepository;
    private final IncomingDetailMapper incomingDetailMapper = IncomingDetailMapper.INSTANCE;

    @Override
    public List<IncomingDetailDTO> getAllIncomingDetail() {
        List<IncomingDetail> incomingDetails = incomingDetailRepository.findAll();
        if (incomingDetails.isEmpty()) {
            throw WarehouseException.IncomingDetailNotFound();
        }
        return incomingDetailMapper.toDtos(incomingDetails);
    }

    public IncomingDetailDTO findIncomingDetailById(Long incomingId) {
        IncomingDetail incomingDetail = incomingDetailRepository.findById(incomingId).orElseThrow(WarehouseException::IncomingDetailNotFound);
        return incomingDetailMapper.toDto(incomingDetail);
    }

    @Override
    public void deleteIncomingDetail(Long incomingId) {
        log.info("delete incoming detail by id {}", incomingId);
        incomingDetailRepository.deleteById(incomingId);
    }

    @Override
    public IncomingDetailDTO updateIncomingDetail(Long incomingId, IncomingDetailUpdateDTO incomingDetailUpdateDTO) {
        log.info("update incoming detail {}", incomingId);
        IncomingDetail incomingDetail = incomingDetailRepository.findById(incomingId).orElseThrow(WarehouseException::IncomingDetailNotFound);
        if (incomingDetailUpdateDTO.getAmount() <= 0) {
            throw WarehouseException.badRequest("InvalidAmount", "Amount cannot be 0 or below 0!");
        }
        if (incomingDetailUpdateDTO.getCost() < 0) {
            throw WarehouseException.badRequest("InvalidProductCost", "Product cost cannot below 0!");
        }
        incomingDetailMapper.mapFromDto(incomingDetailUpdateDTO, incomingDetail);
        return incomingDetailMapper.toDto(incomingDetailRepository.save(incomingDetail));
    }

    //     Incomings amount for each product before input date
    @Override
    public List<IncomingAmountStatsDTO> getNumberOfProductIncoming(LocalDateTime date) {
        if (date.isAfter(LocalDateTime.now())) {
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDateTime.now());
        }
        return incomingDetailRepository.getNumberOfProductIncoming(date);
    }

    @Override
    public List<PurchaseTimeStatDTO> getNumberOfPurchaseTimeAndAmount() {
        return incomingDetailRepository.getNumberOfPurchaseTimeAndAmount();
    }

    @Override
    public PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProduct(Long inputId) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        return incomingDetailRepository.getPurchaseTimeAndAmountOfSpecificProduct(inputId);
    }

    @Override
    public PurchaseTimeStatDTO getPurchaseTimeAndAmountOfSpecificProductAndDate(Long inputId, LocalDateTime inputDate) {
        Product product = productRepository.findById(inputId).orElseThrow(() -> new ResponseException("NotFoundProductId", "Product not found with ID " + inputId, HttpStatus.NOT_FOUND));
        if (inputDate.isAfter(LocalDateTime.now())) {
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDateTime.now());
        }
        return incomingDetailRepository.getPurchaseTimeAndAmountOfSpecificProductAndDate(inputId, inputDate);
    }

    @Override
    public List<PurchaseTimeStatDTO> getPurchaseTimeAndAmountBetweenDates(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate.isAfter(LocalDateTime.now()) || toDate.isAfter(LocalDateTime.now())) {
            throw WarehouseException.badRequest("InvalidDate", "Date must not be after " + LocalDateTime.now());
        }
        return incomingDetailRepository.getPurchaseTimeAndAmountBetweenDates(fromDate, toDate);
    }

    @Override
    public List<Object[]> getCountDaysAndAmountBeforeExpire(Long inputCountDays) {
        if (inputCountDays <= 0) {
            throw WarehouseException.badRequest("InvalidCountDays", "Count days cannot be 0 or less than 0");
        }
        return incomingDetailRepository.getCountDaysAndAmountBeforeExpire(inputCountDays);
    }

    @Override
    public List<ProductNearlyOutOfStockStatDTO> getProductNearlyOutOfStock(Double inputAmount) {
        if (inputAmount < 500) {
            throw WarehouseException.badRequest("InvalidAmount", "Warehouse stock amount of each product must be greater than 500 kgs");
        }
        return incomingDetailRepository.getProductNearlyOutOfStock(inputAmount);
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterial() {
        return incomingDetailRepository.getTotalStockAmountOfRawMaterial();
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGood() {
        return incomingDetailRepository.getTotalStockAmountOfFinishedGood();
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfRawMaterialBeforeDate(LocalDateTime inputDate) {
        if (inputDate.isAfter(LocalDateTime.now())) {
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDateTime.now());
        }
        return incomingDetailRepository.getTotalStockAmountOfRawMaterialBeforeDate(inputDate);
    }

    @Override
    public List<StockAmountOfCategoryStatDTO> getTotalStockAmountOfFinishedGoodBeforeDate(LocalDateTime inputDate) {
        if (inputDate.isAfter(LocalDateTime.now())) {
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDateTime.now());
        }
        return incomingDetailRepository.getTotalStockAmountOfFinishedGoodBeforeDate(inputDate);
    }

    @Override
    public List<CostStatsDTO> getProductsTotalCost() {
        return incomingDetailRepository.getProductsTotalCost();
    }

    @Override
    public List<IncomingProductStatDTO> getIncomingAmountOfProduct(Long inputProductId) {
        return incomingDetailRepository.getIncomingAmountOfProduct(inputProductId);
    }

    @Override
    public TotalStockOfProductStatDTO getTotalStockAmountOfProduct(Long inputProductId) {
        return incomingDetailRepository.getTotalStockAmountOfProduct(inputProductId);
    }

    @Override
    public Object getInAmountWithinMonth() {
        return incomingDetailRepository.getInAmountWithinMonth();
    }

    @Override
    public Object getStockAmountNotExpiration() {
        return incomingDetailRepository.getStockAmountNotExpiration();
    }

    @Override
    public List<MonthlyAmountDTO> getMonthLyIncomingAmount() {
        return incomingDetailRepository.getMonthLyIncomingAmount();
    }

    @Override
    public List<YearAmountDTO> getAnnualIncomingAmount() {
        return incomingDetailRepository.getAnnualIncomingAmount();
    }
}

