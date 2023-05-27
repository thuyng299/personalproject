package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierCreateDTO {
    private String supplierName;
    private String supplierCode;
    private String supplierEmail;
    private String supplierPhone;
    private String supplierAddress;
}
