package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.dto.EmployeeCreateDTO;
import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.dto.EmployeeUpdateDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO findEmployeeById (Long employeeId);
    EmployeeDTO createEmployee (EmployeeCreateDTO employeeCreateDTO);
    void deleteEmployee (Long employeeId);
    List<EmployeeDTO> findByFirstName (String firstName);
    EmployeeDTO updateEmployee (Long employeeId, EmployeeUpdateDTO employeeUpdateDTO);
}
