package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.GoodsDeliveryNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;

import java.util.List;

public interface GoodsDeliveryNoteService {
    List<GoodsDeliveryNoteDTO> getAllGoodsDeliveryNote();
    GoodsDeliveryNoteDTO findGoodsDeliveryNoteById (Long gdnId);
    GoodsDeliveryNoteDTO createGoodsDeliveryNote (Long customerId, GoodsDeliveryNoteCreateDTO goodsDeliveryNoteCreateDTO);
    void deleteGoodsDeliveryNote (Long gdnId);
}
