package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryName(String countryName);
    Boolean existsByCountryName (String countryName);
}
