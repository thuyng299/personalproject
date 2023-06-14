package com.nonit.personalproject.dto;

import com.nonit.personalproject.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String phone;
    private Double salary;
    private String position;
    private Boolean status;
    private Long areaId;
    private String role;
}
