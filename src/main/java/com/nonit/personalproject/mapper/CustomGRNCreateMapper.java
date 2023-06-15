package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.GRNCreateWithDetailDTO;
import com.nonit.personalproject.entity.GoodsReceivedNote;
import com.nonit.personalproject.entity.IncomingDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomGRNCreateMapper {
    CustomGRNCreateMapper INSTANCE = Mappers.getMapper(CustomGRNCreateMapper.class);
    @Mapping(source = "goodsReceivedNote.employee.id", target = "employeeId")
//    @Mapping(source = "goodsReceivedNote.supplier.id", target = "supplierId")
//    @Mapping(source = "goodsReceivedNote.incomingsDate", target = "incomingsDate")
//    @Mapping(source = "incomingDetail.amount", target = "amount")
//    @Mapping(source = "incomingDetail.cost", target = "productCost")
//    @Mapping(source = "incomingDetail.remainingAmount", target = "remainingAmount")
//    @Mapping(source = "incomingDetail.expirationDate", target = "expirationDate")
//    @Mapping(source = "incomingDetail.product.id", target = "productId")
//    @Mapping(source = "incomingDetail.warehouseArea.id", target = "areaId")
    GRNCreateWithDetailDTO toDto(GoodsReceivedNote goodsReceivedNote, IncomingDetail incomingDetail);

//        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//        void mapFromDto (GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO, @MappingTarget GoodsReceivedNote goodsReceivedNote);
}
