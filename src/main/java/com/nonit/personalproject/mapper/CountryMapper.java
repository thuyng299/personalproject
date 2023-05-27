package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.CountryDTO;
import com.nonit.personalproject.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    CountryDTO toDto (Country country);
    List<CountryDTO> toDtos(List<Country> countries);
}
