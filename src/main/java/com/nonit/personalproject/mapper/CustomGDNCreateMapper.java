package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.GDNCreateWithDetailsDTO;
import com.nonit.personalproject.dto.GRNCreateWithDetailDTO;
import com.nonit.personalproject.entity.GoodsDeliveryNote;
import com.nonit.personalproject.entity.GoodsReceivedNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomGDNCreateMapper {
    CustomGDNCreateMapper INSTANCE = Mappers.getMapper(CustomGDNCreateMapper.class);
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "id", target = "gdnId")
    GDNCreateWithDetailsDTO toDto(GoodsDeliveryNote goodsDeliveryNote);
}
