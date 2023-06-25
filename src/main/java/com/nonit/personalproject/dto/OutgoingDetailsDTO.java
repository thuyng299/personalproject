package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutgoingDetailsDTO {
    private Double amount;
    private Double price;
    private String productName;
    private Double discount;
}
