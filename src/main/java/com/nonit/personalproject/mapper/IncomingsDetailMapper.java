package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.dto.IncomingsDetailUpdateDTO;
import com.nonit.personalproject.dto.WarehouseAreaCreateDTO;
import com.nonit.personalproject.entity.IncomingsDetail;
import com.nonit.personalproject.entity.WarehouseArea;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomingsDetailMapper {
    IncomingsDetailMapper INSTANCE = Mappers.getMapper(IncomingsDetailMapper.class);
    @Mapping(source = "incomingsDetail.product.productId", target = "productId")
    @Mapping(source = "incomingsDetail.goodsReceivedNote.grnId", target = "grnId")
    IncomingsDetailDTO toDto (IncomingsDetail incomingsDetail);
    List<IncomingsDetailDTO> toDtos (List<IncomingsDetail> incomingsDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (IncomingsDetailUpdateDTO incomingsDetailUpdateDTO, @MappingTarget IncomingsDetail incomingsDetail);
}
