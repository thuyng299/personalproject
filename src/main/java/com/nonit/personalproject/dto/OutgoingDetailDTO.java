package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutgoingDetailDTO {
    private Long id;
    private Double amount;
    private Double price;
    private Double discount;
    private Long productId;
    private Long gdnId;
    private Long incomingId;
}
