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
    @Mapping(source = "supplier.code", target = "supplierCode")
    @Mapping(source = "id", target = "grnId")
    GRNCreateWithDetailDTO toDto(GoodsReceivedNote goodsReceivedNote);

//        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//        void mapFromDto (GoodsReceivedNoteUpdateDTO goodsReceivedNoteUpdateDTO, @MappingTarget GoodsReceivedNote goodsReceivedNote);
}
