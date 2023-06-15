package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.GoodsReceivedNoteDTO;
import com.nonit.personalproject.dto.GoodsReceivedNoteUpdateDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.entity.GoodsReceivedNote;
import com.nonit.personalproject.entity.Supplier;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoodsReceivedNoteMapper {
    GoodsReceivedNoteMapper INSTANCE = Mappers.getMapper(GoodsReceivedNoteMapper.class);
    @Mapping(source = "goodsReceivedNote.employee.id", target = "employeeId")
    @Mapping(source = "goodsReceivedNote.supplier.id", target = "supplierId")
    GoodsReceivedNoteDTO toDto (GoodsReceivedNote goodsReceivedNote);
    List<GoodsReceivedNoteDTO> toDtos (List<GoodsReceivedNote> goodsReceivedNotes);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO, @MappingTarget GoodsReceivedNote goodsReceivedNote);

}
