package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.GDNCreateWithDetailsDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;

import java.util.List;

public interface GoodsDeliveryNoteService {
    List<GoodsDeliveryNoteDTO> getAllGoodsDeliveryNote();
    GoodsDeliveryNoteDTO findGoodsDeliveryNoteById (Long gdnId);
    GoodsDeliveryNoteDTO createGoodsDeliveryNote (GDNCreateWithDetailsDTO gdnCreateWithDetailsDTO);
    void deleteGoodsDeliveryNote (Long gdnId);
}
