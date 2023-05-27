package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.entity.GoodsReceivedNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoodsReceivedNoteMapper {
    GoodsReceivedNoteMapper INSTANCE = Mappers.getMapper(GoodsReceivedNoteMapper.class);
    @Mapping(source = "goodsReceivedNote.employee.employeeId", target = "employeeId")
    @Mapping(source = "goodsReceivedNote.warehouseArea.areaId", target = "areaId")
    @Mapping(source = "goodsReceivedNote.supplier.supplierId", target = "supplierId")
    GoodsReceivedNoteDTO toDto (GoodsReceivedNote goodsReceivedNote);
    List<GoodsReceivedNoteDTO> toDtos (List<GoodsReceivedNote> goodsReceivedNotes);

}
