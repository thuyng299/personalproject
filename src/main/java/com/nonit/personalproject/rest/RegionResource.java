package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.RegionCreateDTO;
import com.nonit.personalproject.dto.RegionDTO;
import com.nonit.personalproject.entity.Region;
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
    public ResponseEntity<RegionDTO> createRegion(RegionCreateDTO regionCreateDTO) {
        RegionDTO createdRegionDTO = regionServiceImpl.createRegion(regionCreateDTO);
        return ResponseEntity.created(URI.create("/regions" + createdRegionDTO.getId())).body(createdRegionDTO);
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
    public ResponseEntity<RegionDTO> findByName(String regionName) {
            return ResponseEntity.ok(regionServiceImpl.findByName(regionName));
    }

    @Override
    public ResponseEntity<List<RegionDTO>> findByIdOrName(Long regionId, String regionName) {
          return ResponseEntity.ok(regionServiceImpl.findByIdOrName(regionId, regionName));
    }

    @Override
    public ResponseEntity<RegionDTO> updateRegion(Long regionId, RegionCreateDTO regionCreateDTO) {
        return ResponseEntity.ok().body(regionServiceImpl.updateRegion(regionId, regionCreateDTO));
    }
}