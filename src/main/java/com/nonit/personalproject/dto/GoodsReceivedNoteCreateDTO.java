package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsReceivedNoteCreateDTO {
    private LocalDate incomingsDate;
//    private Long employeeId;
    private Long areaId;
//    private Long supplierId;
}
