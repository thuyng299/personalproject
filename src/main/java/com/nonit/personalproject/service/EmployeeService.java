package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.EmployeeCreateDTO;
import com.nonit.personalproject.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO findEmployeeById (Long employeeId);
    EmployeeDTO createEmployee (EmployeeCreateDTO employeeCreateDTO);
    void deleteEmployee (Long employeeId);
    List<EmployeeDTO> findByFirstName (String firstName);
}
