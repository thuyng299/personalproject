package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Employee;
import com.nonit.personalproject.entity.Supplier;
import com.nonit.personalproject.entity.WarehouseArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsReceivedNoteDTO {
    private Long grnId;
    private LocalDate incomingsDate;
    private Long employeeId;
    private Long areaId;
    private Long supplierId;
}
