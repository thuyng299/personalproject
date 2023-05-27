package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.EmployeeCreateDTO;
import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.serviceimpl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeResource implements EmployeeAPI{
    private final EmployeeServiceImpl employeeServiceImpl;

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return ResponseEntity.ok(employeeServiceImpl.getAllEmployee());
    }

    @Override
    public ResponseEntity<EmployeeDTO> findEmployeeById(Long employeeId) {
        return ResponseEntity.ok(employeeServiceImpl.findEmployeeById(employeeId));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long employeeId) {
        employeeServiceImpl.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> findByFirstName(String firstName) {
        return ResponseEntity.ok(employeeServiceImpl.findByFirstName(firstName));
    }

    @Override
    public ResponseEntity<EmployeeDTO> createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        EmployeeDTO createdEmployeeDTO = employeeServiceImpl.createEmployee(employeeCreateDTO);
        return ResponseEntity.created(URI.create("/employees" + createdEmployeeDTO.getEmployeeId())).body(createdEmployeeDTO);
    }
}
