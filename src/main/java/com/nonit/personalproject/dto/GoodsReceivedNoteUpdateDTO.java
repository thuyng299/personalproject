package com.nonit.personalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsReceivedNoteUpdateDTO {
    private LocalDate incomingDate;
    private Long areaId;
    private Long supplierId;
}
