//package com.nonit.personalproject.mapper;
//
//import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
//import com.nonit.personalproject.entity.GoodsDeliveryNote;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.ReportingPolicy;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface GoodsDeliveryNoteMapper {
//    GoodsDeliveryNoteMapper INSTANCE = Mappers.getMapper(GoodsDeliveryNoteMapper.class);
////    @Mapping(source = "goodsDeliveryNote.employee.id", target = "id")
////    @Mapping(source = "goodsDeliveryNote.warehouseArea.id", target = "id")
////    @Mapping(source = "goodsDeliveryNote.customer.id", target = "id")
//    GoodsDeliveryNoteDTO toDto (GoodsDeliveryNote goodsDeliveryNote);
//    List<GoodsDeliveryNoteDTO> toDtos (List<GoodsDeliveryNote> goodsDeliveryNotes);
//}
