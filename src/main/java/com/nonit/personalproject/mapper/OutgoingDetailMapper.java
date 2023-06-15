package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.OutgoingDetailDTO;
import com.nonit.personalproject.entity.OutgoingDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OutgoingDetailMapper {
    OutgoingDetailMapper INSTANCE = Mappers.getMapper(OutgoingDetailMapper.class);
    @Mapping(source = "outgoingDetail.product.id", target = "productId")
    @Mapping(source = "outgoingDetail.goodsDeliveryNote.id", target = "gdnId")
    @Mapping(source = "outgoingDetail.incomingDetail.id", target = "incomingId")
    OutgoingDetailDTO toDto (OutgoingDetail outgoingDetail);
    List<OutgoingDetailDTO> toDtos (List<OutgoingDetail> outgoingDetails);
}
