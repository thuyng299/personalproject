package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.IncomingDetailsCreateDto;
import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.dto.IncomingsDetailUpdateDTO;
import com.nonit.personalproject.entity.IncomingDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomingDetailMapper {
    IncomingDetailMapper INSTANCE = Mappers.getMapper(IncomingDetailMapper.class);
    @Mapping(source = "incomingDetail.product.id", target = "productId")
    @Mapping(source = "incomingDetail.goodsReceivedNote.id", target = "grnId")
    IncomingsDetailDTO toDto (IncomingDetail incomingDetail);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "warehouseArea.id", target = "areaId")
    IncomingDetailsCreateDto toReturnDto (IncomingDetail incomingDetail);
    List<IncomingsDetailDTO> toDtos (List<IncomingDetail> incomingDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (IncomingsDetailUpdateDTO incomingsDetailUpdateDTO, @MappingTarget IncomingDetail incomingDetail);
}
