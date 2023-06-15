package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.GRNCreateWithDetailDTO;
import com.nonit.personalproject.entity.GoodsReceivedNote;
import com.nonit.personalproject.entity.IncomingDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomGRNCreateMapper {
    CustomGRNCreateMapper INSTANCE = Mappers.getMapper(CustomGRNCreateMapper.class);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "id", target = "grnId")
//    @Mapping(source = "incomingDetail", target = "incomingDetailsCreateDtoList")

//    @Mapping(source = "incomingDate", target = "incomingDate")
//    @Mapping(source = "record", target = "record")
//    @Mapping(source = "incomingDetails.amount", target = "amount")
//    @Mapping(source = "incomingDetails.cost", target = "cost")
//    @Mapping(source = "incomingDetails.remainingAmount", target = "remainingAmount")
//    @Mapping(source = "incomingDetails.expirationDate", target = "expirationDate")
//    @Mapping(source = "incomingDetails.product.id", target = "productId")
//    @Mapping(source = "incomingDetails.warehouseArea.id", target = "areaId")
    GRNCreateWithDetailDTO toDto(GoodsReceivedNote goodsReceivedNote);

//        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//        void mapFromDto (GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO, @MappingTarget GoodsReceivedNote goodsReceivedNote);
}
