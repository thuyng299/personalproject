package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.CountryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/countries")
public interface CountryAPI {
    @GetMapping
    ResponseEntity<List<CountryDTO>> getAllCountry();
    @GetMapping("/{countryId}")
    ResponseEntity<CountryDTO> findCountryById(@PathVariable("countryId") Long countryId);
    @PostMapping("/{regionId}")
    ResponseEntity<CountryDTO> createCountry(@PathVariable("regionId") Long regionId,
                                             @RequestBody CountryDTO countryDTO);
    @DeleteMapping("/{countryId}")
    ResponseEntity<Void> deleteCountry(@PathVariable("countryId") Long countryId);
    @GetMapping("/countryname")
    ResponseEntity<CountryDTO> findByCountryName(@RequestParam("countryName") String countryName);
}
