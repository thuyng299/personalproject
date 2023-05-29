package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryCreateDTO {
    private String countryName;
    private Long regionId;
}
