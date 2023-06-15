package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.GoodsReceivedNote;
import com.nonit.personalproject.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingsDetailDTO {
    private Long incomingsId;
    private Double incomingsAmount;
    private Double productCost;
    private Double remainingAmount;
    private LocalDateTime expirationDate;
    private Long productId;
    private Long grnId;
}
