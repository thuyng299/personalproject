package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.GoodsDeliveryNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
import com.nonit.personalproject.entity.GoodsDeliveryNote;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.GoodsDeliveryNoteMapper;
import com.nonit.personalproject.repository.CustomerRepository;
import com.nonit.personalproject.repository.EmployeeRepository;
import com.nonit.personalproject.repository.GoodsDeliveryNoteRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.GoodsDeliveryNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodsDeliveryNoteServiceImpl implements GoodsDeliveryNoteService {
    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final CustomerRepository customerRepository;
    private final GoodsDeliveryNoteMapper goodsDeliveryNoteMapper = GoodsDeliveryNoteMapper.INSTANCE;

    @Override
    public List<GoodsDeliveryNoteDTO> getAllGoodsDeliveryNote() {
        List<GoodsDeliveryNote> goodsDeliveryNotes = goodsDeliveryNoteRepository.findAll();
        if (goodsDeliveryNotes.isEmpty()){
            throw WarehouseException.GDNNotFound();
        }
        return goodsDeliveryNoteMapper.toDtos(goodsDeliveryNotes);
    }

    @Override
    public GoodsDeliveryNoteDTO findGoodsDeliveryNoteById(Long gdnId) {
        GoodsDeliveryNote goodsDeliveryNote = goodsDeliveryNoteRepository.findById(gdnId).orElseThrow(WarehouseException::GDNNotFound);
        return goodsDeliveryNoteMapper.toDto(goodsDeliveryNote);
    }

    @Override
    public GoodsDeliveryNoteDTO createGoodsDeliveryNote(Long customerId, GoodsDeliveryNoteCreateDTO goodsDeliveryNoteCreateDTO) {
        return null;
    }

    @Override
    public void deleteGoodsDeliveryNote(Long gdnId) {
        log.info("delete GDN by id {}", gdnId);
        goodsDeliveryNoteRepository.deleteById(gdnId);
    }
}
