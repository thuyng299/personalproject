package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Customer;
import com.nonit.personalproject.entity.Employee;
import com.nonit.personalproject.entity.WarehouseArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDeliveryNoteDTO {
    private Long id;
    private String code;
    private LocalDate outgoingDate;
    private Double totalAmount;
    private String record;
    private Long employeeId;
    private Long customerId;
}
