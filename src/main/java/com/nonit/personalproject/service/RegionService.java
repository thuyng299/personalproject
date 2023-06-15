package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.RegionCreateDTO;
import com.nonit.personalproject.dto.RegionDTO;

import java.util.List;

public interface RegionService {
    List<RegionDTO> getAllRegion();
    RegionDTO findRegionById(Long regionId);
    RegionDTO createRegion(RegionCreateDTO regionCreateDTO);
    void deleteRegion(Long regionId);
    RegionDTO findByName(String regionName);
    List<RegionDTO> findByIdOrName(Long regionId, String regionName);
    RegionDTO updateRegion (Long regionId, RegionCreateDTO regionCreateDTO);

}
