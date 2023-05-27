package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private Long supplierId;
    private String supplierName;
    private String supplierCode;
    private String supplierEmail;
    private String supplierPhone;
    private String supplierAddress;
    private Country country;
}
