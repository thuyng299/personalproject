package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private Long id;
    private String name;
    private String code;
    private String email;
    private String phone;
    private String address;
    private Boolean status;
    private Country country;

}
