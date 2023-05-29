package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.WarehouseAreaCreateDTO;
import com.nonit.personalproject.dto.WarehouseAreaDTO;
import com.nonit.personalproject.entity.Supplier;
import com.nonit.personalproject.entity.WarehouseArea;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WarehouseAreaMapper {
    WarehouseAreaMapper INSTANCE = Mappers.getMapper(WarehouseAreaMapper.class);
    WarehouseAreaDTO toDto (WarehouseArea warehouseArea);
    List<WarehouseAreaDTO> toDtos (List<WarehouseArea> warehouseAreas);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (WarehouseAreaCreateDTO warehouseAreaCreateDTO, @MappingTarget WarehouseArea warehouseArea);
}
