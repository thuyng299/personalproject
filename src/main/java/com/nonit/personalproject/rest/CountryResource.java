package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.CountryCreateDTO;
import com.nonit.personalproject.dto.CountryDTO;
import com.nonit.personalproject.serviceimpl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryResource implements CountryAPI{
    private final CountryServiceImpl countryServiceImpl;
    @Override
    public ResponseEntity<List<CountryDTO>> getAllCountry() {
        return ResponseEntity.ok(countryServiceImpl.getAllCountry());
    }

    @Override
    public ResponseEntity<CountryDTO> findCountryById(Long countryId) {
        return ResponseEntity.ok(countryServiceImpl.findCountryById(countryId));
    }

    @Override
    public ResponseEntity<CountryDTO> createCountry(Long regionId, CountryCreateDTO countryCreateDTO) {
        CountryDTO createdCountryDTO = countryServiceImpl.createCountry(countryCreateDTO, regionId);
        return ResponseEntity.created(URI.create("/countries" + createdCountryDTO.getId())).body(createdCountryDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCountry(Long countryId) {
        countryServiceImpl.deleteCountry(countryId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CountryDTO> findByName(String countryName) {
        return ResponseEntity.ok(countryServiceImpl.findByName(countryName));
    }

    @Override
    public ResponseEntity<CountryDTO> updateCountry(Long countryId, CountryCreateDTO countryCreateDTO) {
        return ResponseEntity.ok().body(countryServiceImpl.updateCountry(countryId, countryCreateDTO));
    }
}
