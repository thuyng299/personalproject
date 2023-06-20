package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutgoingDetailsCreateDTO {
    private Double amount;
    private Double price;

    private Double discount;
    private Long productId;
    private Long incomingId;
}
