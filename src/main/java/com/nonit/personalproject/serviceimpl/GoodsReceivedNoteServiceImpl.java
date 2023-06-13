package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.GRNCreateWithDetailsDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteUpdateDTO;
import com.nonit.personalproject.entity.*;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.CustomGRNCreateMapper;
import com.nonit.personalproject.mapper.GoodsReceivedNoteMapper;
import com.nonit.personalproject.repository.*;
import com.nonit.personalproject.service.GoodsReceivedNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GoodsReceivedNoteServiceImpl implements GoodsReceivedNoteService {
    private final GoodsReceivedNoteRepository goodsReceivedNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final SupplierRepository supplierRepository;
    private final IncomingsDetailRepository incomingsDetailRepository;
    private final ProductRepository productRepository;
    private final GoodsReceivedNoteMapper goodsReceivedNoteMapper = GoodsReceivedNoteMapper.INSTANCE;

    @Override
    public List<GoodsReceivedNoteDTO> getAllGoodsReceivedNote() {
        List<GoodsReceivedNote> goodsReceivedNotes = goodsReceivedNoteRepository.findAll();
        if (goodsReceivedNotes.isEmpty()){
            throw WarehouseException.GRNNotFound();
        }
        return goodsReceivedNoteMapper.toDtos(goodsReceivedNotes);
    }

    @Override
    public GoodsReceivedNoteDTO findGoodsReceivedNoteById(Long grnId) {
        GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepository.findById(grnId).orElseThrow(WarehouseException::GRNNotFound);
        return goodsReceivedNoteMapper.toDto(goodsReceivedNote);
    }
    public String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    @Override
    public GRNCreateWithDetailsDTO createGoodsReceivedNote(GRNCreateWithDetailsDTO grnCreateWithDetailsDTO) {
        Supplier supplier = supplierRepository.findById(grnCreateWithDetailsDTO.getSupplierId()).orElseThrow(WarehouseException::SupplierNotFound);
        WarehouseArea warehouseArea = warehouseAreaRepository.findById(grnCreateWithDetailsDTO.getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
        Employee employee = employeeRepository.findByUsername(getCurrentUsername()).get();

        if (grnCreateWithDetailsDTO.getIncomingsDate().isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }

        // Create GRN
        GoodsReceivedNote goodsReceivedNote = GoodsReceivedNote.builder()
                .incomingsDate(grnCreateWithDetailsDTO.getIncomingsDate())
                .supplier(supplier)
                .employee(employee)
                .warehouseArea(warehouseArea)
                .build();

        goodsReceivedNote = goodsReceivedNoteRepository.save(goodsReceivedNote);

        if (grnCreateWithDetailsDTO.getIncomingsAmount() <= 0){
            throw WarehouseException.badRequest("InvalidIncomingsAmount", "Amount cannot be 0 or below 0!");
        }
        if (grnCreateWithDetailsDTO.getProductCost() < 0){
            throw WarehouseException.badRequest("InvalidProductCost", "Product cost cannot below 0!");
        }
        // Create detail
            IncomingsDetail incomingsDetail = new IncomingsDetail();
            incomingsDetail.setGoodsReceivedNote(goodsReceivedNote);
            incomingsDetail.setIncomingsAmount(grnCreateWithDetailsDTO.getIncomingsAmount());
            incomingsDetail.setRemainingAmount(grnCreateWithDetailsDTO.getIncomingsAmount());
            incomingsDetail.setProductCost(grnCreateWithDetailsDTO.getProductCost());

            // Set expiration date by conditions and product id auto-generate from area id
            Product product = productRepository.findByWarehouseAreaAreaId(goodsReceivedNote.getWarehouseArea().getAreaId());
            incomingsDetail.setProduct(product);

            if (product.getProductCategory().equals(ProductCategory.RAW_MATERIALS)) {
                incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusYears(2L));
            } else if (product.getProductCategory().equals(ProductCategory.FINISHED_GOODS)) {
                incomingsDetail.setExpirationDate(goodsReceivedNote.getIncomingsDate().plusMonths(6L));
            }
        incomingsDetail = incomingsDetailRepository.save(incomingsDetail);
        goodsReceivedNote.setIncomingsDetail(incomingsDetail);

        return CustomGRNCreateMapper.INSTANCE.toDto(goodsReceivedNote, incomingsDetail);
    }

    @Override
    public void deleteGoodsReceivedNote(Long grnId) {
        log.info("delete goods received note by id {}", grnId);
        goodsReceivedNoteRepository.deleteById(grnId);
    }

    @Override
    public GoodsReceivedNoteDTO updateGoodsReceivedNote(Long grnId, GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO) {
        log.info("update goods received note {}", grnId);
        GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepository.findById(grnId).orElseThrow(WarehouseException::GRNNotFound);
        WarehouseArea warehouseArea = warehouseAreaRepository.findById(goodsReceivedNoteUpdateDTO.getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
        Supplier supplier = supplierRepository.findById(goodsReceivedNoteUpdateDTO.getSupplierId()).orElseThrow(WarehouseException::SupplierNotFound);
        if (goodsReceivedNoteUpdateDTO.getIncomingsDate().isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        goodsReceivedNote.setSupplier(supplier);
        goodsReceivedNote.setWarehouseArea(warehouseArea);
        goodsReceivedNoteMapper.mapFromDto(goodsReceivedNoteUpdateDTO, goodsReceivedNote);
        return goodsReceivedNoteMapper.toDto(goodsReceivedNoteRepository.save(goodsReceivedNote));
    }
}
