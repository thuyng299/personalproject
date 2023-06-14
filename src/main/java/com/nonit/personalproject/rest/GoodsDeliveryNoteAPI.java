//package com.nonit.personalproject.rest;
//
//import com.nonit.personalproject.dto.GoodsDeliveryNoteCreateDTO;
//import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping(value = "/goodsdeliverynotes")
//public interface GoodsDeliveryNoteAPI {
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping
//    ResponseEntity<List<GoodsDeliveryNoteDTO>> getAllGoodsDeliveryNote();
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/{gdnId}")
//    ResponseEntity<GoodsDeliveryNoteDTO> findGoodsDeliveryNoteById(@PathVariable("gdnId") Long gdnId);
//
//    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
//    @PostMapping("/{customerId}")
//    ResponseEntity<GoodsDeliveryNoteDTO> createGoodsDeliveryNote(@PathVariable("customerId") Long customerId,
//                                                                 @RequestBody GoodsDeliveryNoteCreateDTO goodsDeliveryNoteCreateDTO);
//
//    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
//    @DeleteMapping("/{gdnId}")
//    ResponseEntity<Void> deleteGoodsDeliveryNote(@PathVariable("gdnId") Long gdnId);
//}
//
