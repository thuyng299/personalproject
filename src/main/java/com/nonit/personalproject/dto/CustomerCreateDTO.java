package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO {
    private String customerName;
    private String customerCode;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
}
