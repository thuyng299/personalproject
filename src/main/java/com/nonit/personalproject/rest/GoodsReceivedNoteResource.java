package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GoodsReceivedNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.serviceimpl.GoodsReceivedNoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GoodsReceivedNoteResource implements GoodsReceivedNoteAPI {
    private final GoodsReceivedNoteServiceImpl goodsReceivedNoteServiceImpl;

    @Override
    public ResponseEntity<List<GoodsReceivedNoteDTO>> getAllGoodsReceivedNote() {
        return ResponseEntity.ok(goodsReceivedNoteServiceImpl.getAllGoodsReceivedNote());
    }

    @Override
    public ResponseEntity<GoodsReceivedNoteDTO> findGoodsReceivedNoteById(Long grnId) {
        return ResponseEntity.ok(goodsReceivedNoteServiceImpl.findGoodsReceivedNoteById(grnId));
    }

    @Override
    public ResponseEntity<GoodsReceivedNoteDTO> createGoodsReceivedNote(Long supplierId, GoodsReceivedNoteCreateDTO goodsReceivedNoteCreateDTO) {
        GoodsReceivedNoteDTO createdGoodsReceivedNoteDTO = goodsReceivedNoteServiceImpl.createGoodsReceivedNote(supplierId, goodsReceivedNoteCreateDTO);
        return ResponseEntity.created(URI.create("/goodsreceivednotes" + createdGoodsReceivedNoteDTO.getGrnId())).body(createdGoodsReceivedNoteDTO);
    }

    @Override
    public ResponseEntity<Void> deleteGoodsReceivedNote(Long grnId) {
        goodsReceivedNoteServiceImpl.deleteGoodsReceivedNote(grnId);
        return ResponseEntity.noContent().build();
    }
}
