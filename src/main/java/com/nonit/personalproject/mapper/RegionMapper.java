package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.RegionDTO;
import com.nonit.personalproject.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionMapper {
    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);
    RegionDTO toDto (Region region);
    List<RegionDTO> toDtos (List<Region> regions);
}
