package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GRNCreateWithDetailDTO;
import com.nonit.personalproject.dto.GRNWithDetailsDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteUpdateDTO;
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
    public ResponseEntity<List<GRNWithDetailsDTO>> getAllGoodsReceivedNoteWithDetails() {
        return ResponseEntity.ok(goodsReceivedNoteServiceImpl.getAllGoodsReceivedNoteWithDetails());
    }

    @Override
    public ResponseEntity<GoodsReceivedNoteDTO> findGoodsReceivedNoteById(Long grnId) {
        return ResponseEntity.ok(goodsReceivedNoteServiceImpl.findGoodsReceivedNoteById(grnId));
    }

    @Override
    public ResponseEntity<GRNCreateWithDetailDTO> createGoodsReceivedNote(GRNCreateWithDetailDTO grnCreateWithDetailDTO) {
        GRNCreateWithDetailDTO createdGoodsReceivedNoteDTO = goodsReceivedNoteServiceImpl.createGoodsReceivedNote(grnCreateWithDetailDTO);
        return ResponseEntity.created(URI.create("/goodsreceivednotes" + createdGoodsReceivedNoteDTO.getGrnId())).body(createdGoodsReceivedNoteDTO);
    }

    @Override
    public ResponseEntity<Void> deleteGoodsReceivedNote(Long grnId) {
        goodsReceivedNoteServiceImpl.deleteGoodsReceivedNote(grnId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<GoodsReceivedNoteDTO> updateGoodsReceivedNote(Long grnId, GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO) {
        return ResponseEntity.ok().body(goodsReceivedNoteServiceImpl.updateGoodsReceivedNote(grnId, goodsReceivedNoteUpdateDTO));
    }
}