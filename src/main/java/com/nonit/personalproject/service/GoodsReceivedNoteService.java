package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.GoodsReceivedNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;

import java.util.List;

public interface GoodsReceivedNoteService {
    List<GoodsReceivedNoteDTO> getAllGoodsReceivedNote();
    GoodsReceivedNoteDTO findGoodsReceivedNoteById (Long grnId);
    GoodsReceivedNoteDTO createGoodsReceivedNote (Long supplierId, GoodsReceivedNoteCreateDTO goodsReceivedNoteCreateDTO);
    void deleteGoodsReceivedNote (Long grnId);
}
