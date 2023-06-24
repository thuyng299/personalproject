package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.GRNCreateWithDetailDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteUpdateDTO;

import java.util.List;

public interface GoodsReceivedNoteService {
    List<GRNCreateWithDetailDTO> getAllGoodsReceivedNoteWithDetails();
    GoodsReceivedNoteDTO findGoodsReceivedNoteById (Long grnId);
    GRNCreateWithDetailDTO createGoodsReceivedNote(GRNCreateWithDetailDTO grnCreateWithDetailDTO);
     void deleteGoodsReceivedNote (Long grnId);
    GoodsReceivedNoteDTO updateGoodsReceivedNote (Long grnId, GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO);
}
