package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
import com.nonit.personalproject.entity.GoodsDeliveryNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoodsDeliveryNoteMapper {
    GoodsDeliveryNoteMapper INSTANCE = Mappers.getMapper(GoodsDeliveryNoteMapper.class);
    @Mapping(source = "goodsDeliveryNote.employee.employeeId", target = "employeeId")
    @Mapping(source = "goodsDeliveryNote.warehouseArea.areaId", target = "areaId")
    @Mapping(source = "goodsDeliveryNote.customer.customerId", target = " customerId")
    GoodsDeliveryNoteDTO toDto (GoodsDeliveryNote goodsDeliveryNote);
    List<GoodsDeliveryNoteDTO> toDtos (List<GoodsDeliveryNote> goodsDeliveryNotes);
}
