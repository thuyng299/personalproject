package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.CountryCreateDTO;
import com.nonit.personalproject.dto.CountryDTO;
import com.nonit.personalproject.entity.Country;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    CountryDTO toDto (Country country);
    List<CountryDTO> toDtos(List<Country> countries);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (CountryCreateDTO countryCreateDTO, @MappingTarget Country country);
}
