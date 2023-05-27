package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.IncomingsDetailDTO;
import com.nonit.personalproject.entity.IncomingsDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomingsDetailMapper {
    IncomingsDetailMapper INSTANCE = Mappers.getMapper(IncomingsDetailMapper.class);
    @Mapping(source = "incomingsDetail.product.productId", target = "productId")
    @Mapping(source = "incomingsDetail.goodsReceivedNote.grnId", target = "grnId")
    IncomingsDetailDTO toDto (IncomingsDetail incomingsDetail);
    List<IncomingsDetailDTO> toDtos (List<IncomingsDetail> incomingsDetails);
}
