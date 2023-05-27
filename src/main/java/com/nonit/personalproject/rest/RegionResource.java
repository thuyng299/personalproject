package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.RegionDTO;
import com.nonit.personalproject.serviceimpl.RegionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegionResource implements RegionAPI {
    private final RegionServiceImpl regionServiceImpl;

    @Override
    public ResponseEntity<List<RegionDTO>> getAllRegion() {
            return ResponseEntity.ok(regionServiceImpl.getAllRegion());
    }

    @Override
    public ResponseEntity<RegionDTO> createRegion(RegionDTO regionDTO) {
        RegionDTO createdRegionDTO = regionServiceImpl.createRegion(regionDTO);
        return ResponseEntity.created(URI.create("/regions" + createdRegionDTO.getRegionId())).body(createdRegionDTO);
    }

    @Override
    public ResponseEntity<Void> deleteRegion(Long regionId) {
            regionServiceImpl.deleteRegion(regionId);
            return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RegionDTO> findRegionById(Long regionId) {
            return ResponseEntity.ok(regionServiceImpl.findRegionById(regionId));
    }

    @Override
    public ResponseEntity<RegionDTO> findByRegionName(String regionName) {
            return ResponseEntity.ok(regionServiceImpl.findByRegionName(regionName));
    }

    @Override
    public ResponseEntity<List<RegionDTO>> findByRegionIdOrRegionName(Long regionId, String regionName) {
          return ResponseEntity.ok(regionServiceImpl.findByRegionIdOrRegionName(regionId, regionName));
    }
}