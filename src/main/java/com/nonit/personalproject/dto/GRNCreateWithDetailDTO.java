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
public class GRNCreateWithDetailDTO {
    private Long grnId;
    private String code;
    private String supplierCode;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime incomingDate;
    private Long employeeId;
    private String record;
    // Incoming details
    private List<IncomingDetailsCreateDTO> incomingDetailsCreateDTOList;
}
