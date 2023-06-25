package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.GRNCreateWithDetailDTO;
import com.nonit.personalproject.dto.GRNWithDetailsDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/goodsreceivednotes")
public interface GoodsReceivedNoteAPI {

//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping
    ResponseEntity<List<GRNWithDetailsDTO>> getAllGoodsReceivedNoteWithDetails();

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/{grnId}")
    ResponseEntity<GoodsReceivedNoteDTO> findGoodsReceivedNoteById(@PathVariable("grnId") Long grnId);

    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
    @PostMapping
    ResponseEntity<GRNCreateWithDetailDTO> createGoodsReceivedNote(@RequestBody GRNCreateWithDetailDTO grnCreateWithDetailDTO);

    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
    @DeleteMapping("/{grnId}")
    ResponseEntity<Void> deleteGoodsReceivedNote(@PathVariable("grnId") Long grnId);

    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
    @PutMapping("/{grnId}")
    ResponseEntity<GoodsReceivedNoteDTO> updateGoodsReceivedNote(@PathVariable("grnId") Long grnId,
                                                                 @RequestBody GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO);
}
