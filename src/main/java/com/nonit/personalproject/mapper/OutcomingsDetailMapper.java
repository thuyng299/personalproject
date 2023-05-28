package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.OutcomingsDetailDTO;
import com.nonit.personalproject.entity.OutcomingsDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OutcomingsDetailMapper {
    OutcomingsDetailMapper INSTANCE = Mappers.getMapper(OutcomingsDetailMapper.class);
    @Mapping(source = "outcomingsDetail.product.productId", target = "productId")
    @Mapping(source = "outcomingsDetail.goodsDeliveryNote.gdnId", target = "gdnId")
    OutcomingsDetailDTO toDto (OutcomingsDetail outcomingsDetail);
    List<OutcomingsDetailDTO> toDtos (List<OutcomingsDetail> outcomingsDetails);
}
