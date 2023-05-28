package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    List<CountryDTO> getAllCountry();
    CountryDTO findCountryById (Long countryId);
    CountryDTO createCountry (CountryDTO countryDTO, Long regionId);
    void deleteCountry (Long countryId);
    CountryDTO findByCountryName (String countryName);
    CountryDTO updateCountry (Long countryId, CountryDTO countryDTO);
}
