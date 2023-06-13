package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.GRNCreateWithDetailsDTO;
import com.nonit.personalproject.entity.GoodsReceivedNote;
import com.nonit.personalproject.entity.IncomingsDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomGRNCreateMapper {
    CustomGRNCreateMapper INSTANCE = Mappers.getMapper(CustomGRNCreateMapper.class);
    @Mapping(source = "goodsReceivedNote.employee.employeeId", target = "employeeId")
    @Mapping(source = "goodsReceivedNote.warehouseArea.areaId", target = "areaId")
    @Mapping(source = "goodsReceivedNote.supplier.supplierId", target = "supplierId")
    @Mapping(source = "goodsReceivedNote.incomingsDate", target = "incomingsDate")
    @Mapping(source = "incomingsDetail.incomingsAmount", target = "incomingsAmount")
    @Mapping(source = "incomingsDetail.productCost", target = "productCost")
    @Mapping(source = "incomingsDetail.remainingAmount", target = "remainingAmount")
    @Mapping(source = "incomingsDetail.expirationDate", target = "expirationDate")
    @Mapping(source = "incomingsDetail.product.productId", target = "productId")
    GRNCreateWithDetailsDTO toDto(GoodsReceivedNote goodsReceivedNote, IncomingsDetail incomingsDetail);

//        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//        void mapFromDto (GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO, @MappingTarget GoodsReceivedNote goodsReceivedNote);
}
