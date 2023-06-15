package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.CountryCreateDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import com.nonit.personalproject.entity.Country;
import com.nonit.personalproject.entity.Supplier;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);
    SupplierDTO toDto (Supplier supplier);
    List<SupplierDTO> toDtos (List<Supplier> suppliers);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (SupplierCreateDTO supplierCreateDTO, @MappingTarget Supplier supplier);
}
