package com.nonit.personalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nonit.personalproject.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private Double salary;
    private String position;
    private Boolean status;
    private Long areaId;
    private String username;
    private String password;
    private Role role;

}
