package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GoodsReceivedNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/goodsreceivednotes")
public interface GoodsReceivedNoteAPI {
    @GetMapping
    ResponseEntity<List<GoodsReceivedNoteDTO>> getAllGoodsReceivedNote();
    @GetMapping("/{grnId}")
    ResponseEntity<GoodsReceivedNoteDTO> findGoodsReceivedNoteById(@PathVariable("grnId") Long grnId);
    @PostMapping("/{supplierId}")
    ResponseEntity<GoodsReceivedNoteDTO> createGoodsReceivedNote(@PathVariable("supplierId") Long supplierId,
                                                                 @RequestBody GoodsReceivedNoteCreateDTO goodsReceivedNoteCreateDTO);
    @DeleteMapping("/{grnId}")
    ResponseEntity<Void> deleteGoodsReceivedNote(@PathVariable("grnId") Long grnId);
    @PutMapping("/{grnId}")
    ResponseEntity<GoodsReceivedNoteDTO> updateGoodsReceivedNote(@PathVariable("grnId")Long grnId,
                                                                 @RequestBody GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO);
}
