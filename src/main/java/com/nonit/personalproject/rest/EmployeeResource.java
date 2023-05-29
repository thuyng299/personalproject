package com.nonit.personalproject.rest;

import com.lowagie.text.DocumentException;
import com.nonit.personalproject.dto.EmployeeCreateDTO;
import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.dto.EmployeeUpdateDTO;
import com.nonit.personalproject.serviceimpl.EmployeePDFGenerator;
import com.nonit.personalproject.serviceimpl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public ResponseEntity<EmployeeDTO> updateEmployee(Long employeeId, EmployeeUpdateDTO employeeUpdateDTO) {
        return ResponseEntity.ok().body(employeeServiceImpl.updateEmployee(employeeId, employeeUpdateDTO));
    }

    @Override
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=EmployeesList" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List<EmployeeDTO> listOfEmployees  = employeeServiceImpl.getAllEmployee();
        EmployeePDFGenerator generator = new EmployeePDFGenerator();
        generator.generate(listOfEmployees, response);

    }
}
