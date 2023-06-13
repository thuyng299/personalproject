package com.nonit.personalproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GRNCreateWithDetailsDTO {
    private Long grnId;
    private Long supplierId;
    private Long areaId;
    private LocalDate incomingsDate;
    private Long employeeId;
    private Double incomingsAmount;
    private Double productCost;
    private Double remainingAmount;
    private LocalDate expirationDate;
    private Long productId;

}
