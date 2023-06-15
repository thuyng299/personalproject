package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.RegionCreateDTO;
import com.nonit.personalproject.dto.RegionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'WAREHOUSE_STAFF')")
@RequestMapping(value = "/regions")
public interface RegionAPI {
    @GetMapping
    ResponseEntity<List<RegionDTO>> getAllRegion();
    @PostMapping
    ResponseEntity<RegionDTO> createRegion(@RequestBody RegionCreateDTO regionCreateDTO);

    @DeleteMapping("/{regionId}")
    ResponseEntity<Void> deleteRegion(@PathVariable("regionId") Long regionId);

    @GetMapping("/{regionId}") // localhost:8080/regions/3
    ResponseEntity<RegionDTO> findRegionById(@PathVariable("regionId") Long regionId);

    @GetMapping("/name") //localhost:8080/regions/name?regionName=Oceania
    ResponseEntity<RegionDTO> findByName(@RequestParam("regionName") String regionName);

    @GetMapping("/regionid-name/{regionId}") // localhost:8080/regions/regionid-name/6?regionName=Asia
    ResponseEntity<List<RegionDTO>> findByIdOrName(@PathVariable("regionId") Long regionId,
                                                         @RequestParam("regionName") String regionName);

    @PutMapping("/{regionId}")
    ResponseEntity<RegionDTO> updateRegion(@PathVariable("regionId") Long regionId,
                                           @RequestBody RegionCreateDTO regionCreateDTO);

}
