package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GoodsReceivedNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
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
    @DeleteMapping
    ResponseEntity<Void> deleteGoodsReceivedNote(@PathVariable("grnId") Long grnId);
}
