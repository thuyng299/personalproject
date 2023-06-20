package com.nonit.personalproject.dto.customstatsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingProductStatDTO {
    private Long grnId;
    private LocalDateTime incomingDate;
    private Double amount;
    private Double remainingAmount;
    private Long productId;
}
