package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.GRNCreateWithDetailsDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteCreateDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteUpdateDTO;

import java.util.List;

public interface GoodsReceivedNoteService {
    List<GoodsReceivedNoteDTO> getAllGoodsReceivedNote();
    GoodsReceivedNoteDTO findGoodsReceivedNoteById (Long grnId);
    GRNCreateWithDetailsDTO createGoodsReceivedNote(GRNCreateWithDetailsDTO grnCreateWithDetailsDTO);
     void deleteGoodsReceivedNote (Long grnId);
    GoodsReceivedNoteDTO updateGoodsReceivedNote (Long grnId, GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO);
}
