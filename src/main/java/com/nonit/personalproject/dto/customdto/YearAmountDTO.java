package com.nonit.personalproject.dto.customdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearAmountDTO {
    private String code;
    private String month;
    private Double totalAmount;
}
