package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingsProductStatDTO {
    private Long grnId;
    private LocalDate incomingsDate;
    private Double incomingsAmount;
    private Double remainingAmount;
    private Long productId;
}
