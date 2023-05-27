package com.nonit.personalproject.rest;

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
    public ResponseEntity<CountryDTO> createCountry(Long regionId, CountryDTO countryDTO) {
        CountryDTO createdCountryDTO = countryServiceImpl.createCountry(countryDTO, regionId);
        return ResponseEntity.created(URI.create("/countries" + createdCountryDTO.getCountryId())).body(createdCountryDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCountry(Long countryId) {
        countryServiceImpl.deleteCountry(countryId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CountryDTO> findByCountryName(String countryName) {
        return ResponseEntity.ok(countryServiceImpl.findByCountryName(countryName));
    }
}
