package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierCreateDTO {
    private String name;
    private String code;
    private String email;
    private String phone;
    private String address;
    private Boolean status;
}
