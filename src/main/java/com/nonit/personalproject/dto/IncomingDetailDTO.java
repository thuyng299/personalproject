package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingDetailDTO {
    private Long id;
    private Double amount;
    private Double cost;
    private Double remainingAmount;
    private LocalDateTime expirationDate;
    private Long productId;
    private Long grnId;
}
