package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.IncomingsDetailCreateDTO;
import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.entity.*;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.IncomingsDetailMapper;
import com.nonit.personalproject.repository.GoodsReceivedNoteRepository;
import com.nonit.personalproject.repository.IncomingsDetailRepository;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.IncomingsDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info("delete incomings by id {}", incomingsId);
        incomingsDetailRepository.deleteById(incomingsId);
    }

    @Override
    public List<Object[]> getNumberOfProductIncomings(LocalDate date) {
        return incomingsDetailRepository.getNumberOfProductIncomings(date);
    }
}
