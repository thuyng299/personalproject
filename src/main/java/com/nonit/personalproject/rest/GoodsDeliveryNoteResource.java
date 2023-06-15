package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GoodsDeliveryNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
import com.nonit.personalproject.serviceimpl.GoodsDeliveryNoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GoodsDeliveryNoteResource implements GoodsDeliveryNoteAPI {
    private final GoodsDeliveryNoteServiceImpl goodsDeliveryNoteServiceImpl;

    @Override
    public ResponseEntity<List<GoodsDeliveryNoteDTO>> getAllGoodsDeliveryNote() {
        return ResponseEntity.ok(goodsDeliveryNoteServiceImpl.getAllGoodsDeliveryNote());
    }

    @Override
    public ResponseEntity<GoodsDeliveryNoteDTO> findGoodsDeliveryNoteById(Long gdnId) {
        return ResponseEntity.ok(goodsDeliveryNoteServiceImpl.findGoodsDeliveryNoteById(gdnId));
    }

    @Override
    public ResponseEntity<GoodsDeliveryNoteDTO> createGoodsDeliveryNote(Long customerId, GoodsDeliveryNoteCreateDTO goodsDeliveryNoteCreateDTO) {
        GoodsDeliveryNoteDTO createdgoodsDeliveryNoteDTO = goodsDeliveryNoteServiceImpl.createGoodsDeliveryNote(customerId, goodsDeliveryNoteCreateDTO);
        return ResponseEntity.created(URI.create("/goodsdeliverynotes" + createdgoodsDeliveryNoteDTO.getId())).body(createdgoodsDeliveryNoteDTO);
    }

    @Override
    public ResponseEntity<Void> deleteGoodsDeliveryNote(Long gdnId) {
        goodsDeliveryNoteServiceImpl.deleteGoodsDeliveryNote(gdnId);
        return ResponseEntity.noContent().build();
    }
}
