package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.WarehouseAreaDTO;
import com.nonit.personalproject.entity.WarehouseArea;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WarehouseAreaMapper {
    WarehouseAreaMapper INSTANCE = Mappers.getMapper(WarehouseAreaMapper.class);
    WarehouseAreaDTO toDto (WarehouseArea warehouseArea);
    List<WarehouseAreaDTO> toDtos (List<WarehouseArea> warehouseAreas);
}
