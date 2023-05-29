package com.nonit.personalproject.rest;

import com.lowagie.text.DocumentException;
import com.nonit.personalproject.dto.EmployeeCreateDTO;
import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.dto.EmployeeUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/employees")
public interface EmployeeAPI {
    @GetMapping
    ResponseEntity<List<EmployeeDTO>> getAllEmployee();
    @GetMapping("/{employeeId}")
    ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("employeeId") Long employeeId);
    @PostMapping
    ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeCreateDTO employeeCreateDTO);
    @DeleteMapping("/{employeeId}")
    ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") Long employeeId);
    @GetMapping("/firstname")
    ResponseEntity<List<EmployeeDTO>> findByFirstName(@RequestParam("firstName") String firstName);
    @PutMapping("/{employeeId}")
    ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("employeeId") Long employeeId,
                                               @RequestBody EmployeeUpdateDTO employeeUpdateDTO);
    @GetMapping("/export-to-pdf")
    public void generatePdfFile (HttpServletResponse response) throws DocumentException, IOException;
}
