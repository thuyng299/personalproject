package com.nonit.personalproject.dto.customstatsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingAmountStatsDTO {
    private Long id;
    private String name;
    private Double totalAmount;
}
