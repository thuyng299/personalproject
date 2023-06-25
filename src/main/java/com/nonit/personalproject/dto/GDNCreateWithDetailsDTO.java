package com.nonit.personalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GDNCreateWithDetailsDTO {
    private Long gdnId;

    private Long customerId;
    private String customerName;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime outgoingDate;
    private String employeeName;

    private Long employeeId;
    private String record;

    // Outgoing details
    private List<OutgoingDetailsCreateDTO> outgoingDetailsCreateDTOList;

}
