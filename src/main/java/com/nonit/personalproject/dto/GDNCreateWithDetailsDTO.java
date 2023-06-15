package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GDNCreateWithDetailsDTO {
    private Long gdnId;
    private String code;
    private LocalDate outcomingsDate;
    private Double totalAmount;
    private Long employeeId;
}
