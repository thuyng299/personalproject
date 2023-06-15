package com.nonit.personalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GRNCreateWithDetailDTO {
    private Long grnId;
    private Long supplierId;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime incomingDate;
    private Long employeeId;
    private String record;
    // Incoming details
    private List<IncomingDetailsCreateDto> incomingDetailsCreateDtoList;
}
