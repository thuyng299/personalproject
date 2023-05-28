package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.RegionDTO;

import java.util.List;

public interface RegionService {
    List<RegionDTO> getAllRegion();
    RegionDTO findRegionById(Long regionId);
    RegionDTO createRegion(RegionDTO regionDTO);
    void deleteRegion(Long regionId);
    RegionDTO findByRegionName(String regionName);
    List<RegionDTO> findByRegionIdOrRegionName(Long regionId, String regionName);
    RegionDTO updateRegion (Long regionId, RegionDTO regionDTO);

}
