package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.CountryCreateDTO;
import com.nonit.personalproject.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    List<CountryDTO> getAllCountry();
    CountryDTO findCountryById (Long countryId);
    CountryDTO createCountry (CountryCreateDTO countryCreateDTO, Long regionId);
    void deleteCountry (Long countryId);
    CountryDTO findByCountryName (String countryName);
    CountryDTO updateCountry (Long countryId, CountryCreateDTO countryCreateDTO);
}
