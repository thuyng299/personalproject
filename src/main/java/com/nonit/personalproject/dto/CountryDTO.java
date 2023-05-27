package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private Long countryId;
    private String countryName;
    private Region region;
}
