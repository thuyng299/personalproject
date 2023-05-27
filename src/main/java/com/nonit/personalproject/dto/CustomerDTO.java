package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long customerId;
    private String customerName;
    private String customerCode;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private Country country;
}
