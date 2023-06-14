//package com.nonit.personalproject.serviceimpl;
//
//import com.nonit.personalproject.dto.CountryCreateDTO;
//import com.nonit.personalproject.dto.CountryDTO;
//import com.nonit.personalproject.entity.Country;
//import com.nonit.personalproject.entity.Region;
//import com.nonit.personalproject.exception.WarehouseException;
//import com.nonit.personalproject.mapper.CountryMapper;
//import com.nonit.personalproject.repository.CountryRepository;
//import com.nonit.personalproject.repository.RegionRepository;
//import com.nonit.personalproject.service.CountryService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class CountryServiceImpl implements CountryService {
//    private final CountryRepository countryRepository;
//    private final RegionRepository regionRepository;
//    private final CountryMapper countryMapper = CountryMapper.INSTANCE;
//
//    @Override
//    public List<CountryDTO> getAllCountry() {
//        List<Country> countries = countryRepository.findAll();
//        if (countries.isEmpty()){
//            throw WarehouseException.CountryNotFound();
//        }
//        return countryMapper.toDtos(countries);
//    }
//
//    @Override
//    public CountryDTO findCountryById(Long countryId) {
//        Country country = countryRepository.findById(countryId).orElseThrow(WarehouseException::CountryNotFound);
//        return countryMapper.toDto(country);
//    }
//
//    @Override
//    public CountryDTO createCountry(CountryCreateDTO countryCreateDTO, Long regionId) {
//        Region region = regionRepository.findById(regionId).orElseThrow(WarehouseException::RegionNotFound);
//        if(countryCreateDTO.getName() == null || countryCreateDTO.getName().trim().isBlank() || countryCreateDTO.getName().isEmpty()){
//            throw WarehouseException.badRequest("InvalidName", "Country name cannot be null!");
//        }
//        if (countryRepository.existsByCountryName(countryCreateDTO.getName())){
//            throw WarehouseException.badRequest("CountryNameExisted", "Country name is already taken!");
//        }
//        Country country = Country.builder()
//                .name(countryCreateDTO.getName())
//                .region(region)
//                .build();
//        country = countryRepository.save(country);
//        return countryMapper.toDto(country);
//    }
//
//    @Override
//    public void deleteCountry(Long countryId) {
//        log.info("delete country by id {}", countryId);
//        countryRepository.deleteById(countryId);
//    }
//
//    @Override
//    public CountryDTO findByCountryName(String countryName) {
//        Country country = countryRepository.findByCountryName(countryName);
//        if (countryName == null || countryName.trim().isBlank() || countryName.isEmpty()){
//            throw WarehouseException.badRequest("InvalidName", "Country name cannot be null!");
//        }
//        return countryMapper.toDto(country);
//    }
//
//    @Override
//    public CountryDTO updateCountry(Long countryId, CountryCreateDTO countryCreateDTO) {
//        log.info("update country by country id {}", countryId);
//        Country country = countryRepository.findById(countryId).orElseThrow(WarehouseException::CountryNotFound);
//        if (countryRepository.existsByCountryName(countryCreateDTO.getName())){
//            throw WarehouseException.badRequest("CountryNameExisted", "Country name already exists!");
//        }
//        countryMapper.mapFromDto(countryCreateDTO, country);
//        return countryMapper.toDto(countryRepository.save(country));
//    }
//}
