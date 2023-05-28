package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.RegionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/regions")
public interface RegionAPI {
    @GetMapping
    ResponseEntity<List<RegionDTO>> getAllRegion();
    @PostMapping
    ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO regionDTO);
    @DeleteMapping("/{regionId}")
    ResponseEntity<Void> deleteRegion(@PathVariable("regionId") Long regionId);
    @GetMapping("/{regionId}")
    ResponseEntity<RegionDTO> findRegionById(@PathVariable("regionId") Long regionId);
    @GetMapping("/name") //localhost:8080/regions/name?regionName=
    ResponseEntity<RegionDTO> findByRegionName(@RequestParam("regionName") String regionName);
    @GetMapping("/regionidorname")
    ResponseEntity<List<RegionDTO>> findByRegionIdOrRegionName(@PathVariable("regionId") Long regionId,
                                                         @RequestParam("regionName") String regionName);
    @PutMapping("/{regionId}")
    ResponseEntity<RegionDTO> updateRegion(@PathVariable("regionId") Long regionId,
                                           @RequestBody RegionDTO regionDTO);

}
