package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.GoodsReceivedNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.entity.Employee;
import com.nonit.personalproject.entity.GoodsReceivedNote;
import com.nonit.personalproject.entity.Supplier;
import com.nonit.personalproject.entity.WarehouseArea;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.GoodsReceivedNoteMapper;
import com.nonit.personalproject.repository.EmployeeRepository;
import com.nonit.personalproject.repository.GoodsReceivedNoteRepository;
import com.nonit.personalproject.repository.SupplierRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.GoodsReceivedNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodsReceivedNoteServiceImpl implements GoodsReceivedNoteService {
    private final GoodsReceivedNoteRepository goodsReceivedNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final SupplierRepository supplierRepository;
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
    public GoodsReceivedNoteDTO createGoodsReceivedNote(Long supplierId, GoodsReceivedNoteCreateDTO goodsReceivedNoteCreateDTO) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(WarehouseException::SupplierNotFound);
        WarehouseArea warehouseArea = warehouseAreaRepository.findById(goodsReceivedNoteCreateDTO.getAreaId()).orElseThrow(WarehouseException::WarehouseAreaNotFound);
        Employee employee = employeeRepository.findByUsername(getCurrentUsername()).get();
        GoodsReceivedNote goodsReceivedNote = GoodsReceivedNote.builder()
                .incomingsDate(goodsReceivedNoteCreateDTO.getIncomingsDate())
                .supplier(supplier)
                .employee(employee)
                .warehouseArea(warehouseArea)
                .build();

        goodsReceivedNote = goodsReceivedNoteRepository.save(goodsReceivedNote);
        return goodsReceivedNoteMapper.toDto(goodsReceivedNote);
    }

    @Override
    public void deleteGoodsReceivedNote(Long grnId) {
        log.info("delete goods received note by id {}", grnId);
        goodsReceivedNoteRepository.deleteById(grnId);
    }
}
