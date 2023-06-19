package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.IncomingDetailsCreateDTO;
import com.nonit.personalproject.dto.IncomingDetailDTO;
import com.nonit.personalproject.dto.IncomingDetailUpdateDTO;
import com.nonit.personalproject.entity.IncomingDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomingDetailMapper {
    IncomingDetailMapper INSTANCE = Mappers.getMapper(IncomingDetailMapper.class);
    @Mapping(source = "incomingDetail.product.id", target = "productId")
    @Mapping(source = "incomingDetail.goodsReceivedNote.id", target = "grnId")
    IncomingDetailDTO toDto (IncomingDetail incomingDetail);

    @Mapping(source = "product.code", target = "productCode")
    @Mapping(source = "warehouseArea.id", target = "areaId")
    IncomingDetailsCreateDTO toReturnDto (IncomingDetail incomingDetail);
    List<IncomingDetailDTO> toDtos (List<IncomingDetail> incomingDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (IncomingDetailUpdateDTO incomingDetailUpdateDTO, @MappingTarget IncomingDetail incomingDetail);
}
