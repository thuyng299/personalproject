package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.entity.*;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.CustomGRNCreateMapper;
import com.nonit.personalproject.mapper.GoodsReceivedNoteMapper;
import com.nonit.personalproject.mapper.IncomingDetailMapper;
import com.nonit.personalproject.repository.*;
import com.nonit.personalproject.service.GoodsReceivedNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final IncomingDetailRepository incomingDetailRepository;
    private final ProductRepository productRepository;
    private final GoodsReceivedNoteMapper goodsReceivedNoteMapper = GoodsReceivedNoteMapper.INSTANCE;
    private final IncomingDetailMapper incomingDetailMapper = IncomingDetailMapper.INSTANCE;

    @Override
    public List<GRNWithDetailsDTO> getAllGoodsReceivedNoteWithDetails() {
        List<GoodsReceivedNote> goodsReceivedNotes = goodsReceivedNoteRepository.findAll();

        if (goodsReceivedNotes.isEmpty()) {
            throw WarehouseException.GRNNotFound();
        }

        List<GRNWithDetailsDTO> grnCreateWithDetailDTOS = new ArrayList<>();

        for (GoodsReceivedNote grn : goodsReceivedNotes) {
            GRNWithDetailsDTO grnCreateWithDetailDTO = new GRNWithDetailsDTO();

            grnCreateWithDetailDTO.setGrnId(grn.getId());
            grnCreateWithDetailDTO.setCode(grn.getCode());
            grnCreateWithDetailDTO.setSupplierName(grn.getSupplier().getName());
            grnCreateWithDetailDTO.setIncomingDate(grn.getIncomingDate());
            grnCreateWithDetailDTO.setEmployeeName(grn.getEmployee().getFirstName() + " " + grn.getEmployee().getLastName());
            grnCreateWithDetailDTO.setRecord(grn.getRecord());

            List<IncomingDetailsDTO> incomingDetailsCreateDTOS = new ArrayList<>();

            for (IncomingDetail incomingDetail : grn.getIncomingDetail()) {

                IncomingDetailsDTO incomingDetailsCreateDTO = new IncomingDetailsDTO();

                incomingDetailsCreateDTO.setAmount(incomingDetail.getAmount());
                incomingDetailsCreateDTO.setCost(incomingDetail.getCost());
                incomingDetailsCreateDTO.setExpirationDate(incomingDetail.getExpirationDate());
                incomingDetailsCreateDTO.setProductName(incomingDetail.getProduct().getName());
                incomingDetailsCreateDTO.setAreaName(incomingDetail.getWarehouseArea().getName());

                incomingDetailsCreateDTOS.add(incomingDetailsCreateDTO);

                grnCreateWithDetailDTO.setIncomingDetailsCreateDTOList(incomingDetailsCreateDTOS);

                grnCreateWithDetailDTOS.add(grnCreateWithDetailDTO);
            }
        }
        return grnCreateWithDetailDTOS;
    }

    @Override
    public GoodsReceivedNoteDTO findGoodsReceivedNoteById(Long grnId) {
        GoodsReceivedNote goodsReceivedNote = goodsReceivedNoteRepository.findById(grnId).orElseThrow(WarehouseException::GRNNotFound);
        return goodsReceivedNoteMapper.toDto(goodsReceivedNote);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public GRNCreateWithDetailDTO createGoodsReceivedNote(GRNCreateWithDetailDTO grnCreateWithDetailDTO) {
        Supplier supplier = supplierRepository.findByCode(grnCreateWithDetailDTO.getSupplierCode()).orElseThrow(WarehouseException::SupplierNotFound);
        Employee employee = employeeRepository.findByUsername(getCurrentUsername()).get();

        // Create GRN
        GoodsReceivedNote goodsReceivedNote = GoodsReceivedNote.builder()
                .incomingDate(LocalDateTime.now())
                .record(grnCreateWithDetailDTO.getRecord())
                .supplier(supplier)
                .employee(employee)
                .code(grnCreateWithDetailDTO.getSupplierCode() + LocalDateTime.now().getMonthValue() + LocalDateTime.now().getDayOfMonth())
                .build();

        // Append the count to the code
        long count = goodsReceivedNoteRepository.countByCode(goodsReceivedNote.getCode());

        if (count > 0) {
            String newCode = goodsReceivedNote.getCode() + "-" + count;
            goodsReceivedNote.setCode(newCode);
        }

        // Create detail
        List<IncomingDetail> incomingDetails = new ArrayList<>();

        for (IncomingDetailsCreateDTO ins : grnCreateWithDetailDTO.getIncomingDetailsCreateDTOList()) {
            if (ins.getAmount() <= 0) {
                throw WarehouseException.badRequest("InvalidAmount", "Amount cannot be 0 or below 0!");
            }
            if (ins.getCost() < 0) {
                throw WarehouseException.badRequest("InvalidProductCost", "Product cost cannot below 0!");
            }
            Product product = productRepository.findByCode(ins.getProductCode()).orElseThrow(WarehouseException::ProductNotFound);
            WarehouseArea warehouseArea = warehouseAreaRepository.findById(ins.getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);

            IncomingDetail incomingDetail = new IncomingDetail();
            incomingDetail.setGoodsReceivedNote(goodsReceivedNote);
            incomingDetail.setAmount(ins.getAmount());
            incomingDetail.setRemainingAmount(ins.getAmount());
            incomingDetail.setCost(ins.getCost());
            incomingDetail.setProduct(product);
            incomingDetail.setWarehouseArea(warehouseArea);

            // Set expiration date based on date of GRN
            if (product.getProductCategory().equals(ProductCategory.RAW_MATERIALS)) {
                incomingDetail.setExpirationDate(goodsReceivedNote.getIncomingDate().plusYears(2L));
            } else if (product.getProductCategory().equals(ProductCategory.FINISHED_GOODS)) {
                incomingDetail.setExpirationDate(goodsReceivedNote.getIncomingDate().plusMonths(6L));
            }

            incomingDetails.add(incomingDetail);

        }
        goodsReceivedNote.setIncomingDetail(incomingDetails);
        goodsReceivedNote = goodsReceivedNoteRepository.save(goodsReceivedNote);

        GRNCreateWithDetailDTO returnGRNCreateWithDetailDTO = CustomGRNCreateMapper.INSTANCE.toDto(goodsReceivedNote);
        List<IncomingDetailsCreateDTO> incomingDetailsCreateDTOList = new ArrayList<>();
        for (IncomingDetail ins : goodsReceivedNote.getIncomingDetail()) {
            IncomingDetailsCreateDTO incomingDetailsCreateDto = incomingDetailMapper.toReturnDto(ins);
            incomingDetailsCreateDTOList.add(incomingDetailsCreateDto);
        }
        returnGRNCreateWithDetailDTO.setIncomingDetailsCreateDTOList(incomingDetailsCreateDTOList);

        return returnGRNCreateWithDetailDTO;
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
        if (goodsReceivedNoteUpdateDTO.getIncomingDate().isAfter(LocalDate.now())) {
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        goodsReceivedNote.setSupplier(supplier);
//        goodsReceivedNote.setWarehouseArea(warehouseArea);
        goodsReceivedNoteMapper.mapFromDto(goodsReceivedNoteUpdateDTO, goodsReceivedNote);
        return goodsReceivedNoteMapper.toDto(goodsReceivedNoteRepository.save(goodsReceivedNote));
    }
}
