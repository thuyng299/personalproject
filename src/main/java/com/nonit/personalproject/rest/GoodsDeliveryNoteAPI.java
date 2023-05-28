package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GoodsDeliveryNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/goodsdeliverynotes")
public interface GoodsDeliveryNoteAPI {
    @GetMapping
    ResponseEntity<List<GoodsDeliveryNoteDTO>> getAllGoodsDeliveryNote();
    @GetMapping("/{gdnId}")
    ResponseEntity<GoodsDeliveryNoteDTO> findGoodsDeliveryNoteById(@PathVariable("gdnId") Long gdnId);
    @PostMapping("/{customerId}")
    ResponseEntity<GoodsDeliveryNoteDTO> createGoodsDeliveryNote(@PathVariable("customerId") Long customerId,
                                                                 @RequestBody GoodsDeliveryNoteCreateDTO goodsDeliveryNoteCreateDTO);
    @DeleteMapping("/{gdnId}")
    ResponseEntity<Void> deleteGoodsDeliveryNote(@PathVariable("gdnId") Long gdnId);
}

