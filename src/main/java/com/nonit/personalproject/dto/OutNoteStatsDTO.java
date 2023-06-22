package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutNoteStatsDTO {
    private String gdnCode;
    private String customerName;
    private String customerEmail;
    private LocalDateTime outgoingDate;
    private Double totalAmount;
}
