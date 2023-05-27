package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.RegionDTO;
import com.nonit.personalproject.entity.Region;
import com.nonit.personalproject.exception.ResponseException;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.RegionMapper;
import com.nonit.personalproject.repository.RegionRepository;
import com.nonit.personalproject.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper = RegionMapper.INSTANCE;

    @Override
    public List<RegionDTO> getAllRegion() {
        List<Region> regions = regionRepository.findAll();
        if (regions.isEmpty()){
            throw WarehouseException.RegionNotFound();
        }
        return regionMapper.toDtos(regions);
    }

    @Override
    public RegionDTO findRegionById(Long regionId) {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new ResponseException("RegionNotFound", "Region not found with id " + regionId, HttpStatus.NOT_FOUND));
        return regionMapper.toDto(region);
    }

    @Override
    public RegionDTO createRegion(RegionDTO regionDTO){
        if (regionDTO.getRegionName() == null || regionDTO.getRegionName().isEmpty() || regionDTO.getRegionName().isBlank()){
            throw WarehouseException.badRequest("InvalidName", "Region name cannot be null!");
        }
        Region region = Region.builder()
                .regionName(regionDTO.getRegionName())
                .build();
        region =  regionRepository.save(region);
        return regionMapper.toDto(region);
    }

    @Override
    public void deleteRegion(Long regionId) {
        log.info("delete region by id {} ", regionId);
        regionRepository.deleteById(regionId);
    }
    @Override
    public RegionDTO findByRegionName(String regionName){
        Region region = regionRepository.findByRegionName(regionName);
        if (regionName == null || regionName.trim().isBlank() || regionName.isEmpty()){
            throw WarehouseException.badRequest("InvalidName", "Region name cannot be null");
        }
        return regionMapper.toDto(region);
    }

    @Override
    public List<RegionDTO> findByRegionIdOrRegionName(Long regionId, String regionName) {
        List<Region> regions = regionRepository.findByRegionIdOrRegionName(regionId, regionName);
        if (regionName == null || regionName.trim().isBlank() || regionName.isEmpty()){
            throw WarehouseException.badRequest("InvalidName", "Region name cannot be null");
        }
        if (regions.isEmpty()){
            throw WarehouseException.RegionNotFound();
        }
        return regionMapper.toDtos(regions);
    }

}
