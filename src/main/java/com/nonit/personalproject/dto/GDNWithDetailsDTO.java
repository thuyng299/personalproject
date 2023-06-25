package com.nonit.personalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GDNWithDetailsDTO {
    private Long gdnId;
    private String customerName;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime outgoingDate;
    private String employeeName;
    private String record;

    // Outgoing details
    private List<OutgoingDetailsDTO> outgoingDetailsCreateDTOList;
}
