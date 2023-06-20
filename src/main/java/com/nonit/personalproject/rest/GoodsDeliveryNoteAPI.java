package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GDNCreateWithDetailsDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/goodsdeliverynotes")
public interface GoodsDeliveryNoteAPI {
    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping
    ResponseEntity<List<GoodsDeliveryNoteDTO>> getAllGoodsDeliveryNote();

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/{gdnId}")
    ResponseEntity<GoodsDeliveryNoteDTO> findGoodsDeliveryNoteById(@PathVariable("gdnId") Long gdnId);

    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
    @PostMapping
    ResponseEntity<GDNCreateWithDetailsDTO> createGoodsDeliveryNote(@RequestBody GDNCreateWithDetailsDTO gdnCreateWithDetailsDTO);

    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
    @DeleteMapping("/{gdnId}")
    ResponseEntity<Void> deleteGoodsDeliveryNote(@PathVariable("gdnId") Long gdnId);
}

