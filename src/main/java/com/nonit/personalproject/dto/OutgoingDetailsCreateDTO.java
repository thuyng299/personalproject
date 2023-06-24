package com.nonit.personalproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutgoingDetailsCreateDTO {
    private Double amount;
    private Double price;
    private String productName;
    private Double discount;
    @JsonIgnore
    private Long productId;
    @JsonIgnore
    private Long incomingId;
}
