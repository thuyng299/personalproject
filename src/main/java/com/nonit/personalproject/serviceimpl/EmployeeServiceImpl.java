package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.EmployeeCreateDTO;
import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.entity.Employee;
import com.nonit.personalproject.entity.Role;
import com.nonit.personalproject.entity.WarehouseArea;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.EmployeeMapper;
import com.nonit.personalproject.repository.EmployeeRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()){
            throw WarehouseException.EmployeeNotFound();
        }
        return employeeMapper.toDtos(employees);
    }

    @Override
    public EmployeeDTO findEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(WarehouseException::EmployeeNotFound);
        return employeeMapper.toDto(employee);
    }
    @Override
    public EmployeeDTO createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        employeeException(employeeCreateDTO);
        Employee employee = Employee.builder()
                .firstName(employeeCreateDTO.getFirstName())
                .lastName(employeeCreateDTO.getLastName())
                .gender(employeeCreateDTO.getGender())
                .email(employeeCreateDTO.getEmail() + "@cashew.com")
                .phone(employeeCreateDTO.getPhone())
                .address(employeeCreateDTO.getAddress())
                .hireDate(employeeCreateDTO.getHireDate())
                .salary(employeeCreateDTO.getSalary())
                .employeePosition(employeeCreateDTO.getEmployeePosition())
                .employeeStatus(Boolean.TRUE)
                .username(employeeCreateDTO.getUsername())
                .password(passwordEncoder.encode(employeeCreateDTO.getPassword()))
                .role(Role.valueOf(employeeCreateDTO.getRole()))
                .build();

        if(employeeCreateDTO.getAreaId() != null) {
            WarehouseArea warehouseArea= warehouseAreaRepository.findById(employeeCreateDTO.getAreaId()).get();
            employee.setWarehouseArea(warehouseArea);
        } else {
            employee.setWarehouseArea(null);
        }
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    private void employeeException(EmployeeCreateDTO employeeCreateDTO) {
        if (employeeCreateDTO.getFirstName() == null || employeeCreateDTO.getFirstName().isEmpty() || employeeCreateDTO.getFirstName().isBlank()){
            throw WarehouseException.badRequest("InvalidFirstName", "First name cannot be null!");
        }
        if (!BooleanChecking.isAlpha(employeeCreateDTO.getFirstName())){
            throw WarehouseException.badRequest("WrongFormatFirstName", "First name must contains only alphabetical characters.");
        }
        if (employeeCreateDTO.getLastName() == null || employeeCreateDTO.getLastName().isBlank() || employeeCreateDTO.getLastName().isEmpty()){
            throw WarehouseException.badRequest("InvalidLastName", "Last name cannot be null!");
        }
        if (!BooleanChecking.isAlpha(employeeCreateDTO.getLastName())){
            throw WarehouseException.badRequest("WrongFormatLastName", "Last name must contains only alphabetical characters.");
        }
        if (employeeCreateDTO.getGender() == null || employeeCreateDTO.getGender().isBlank() || employeeCreateDTO.getGender().isEmpty()){
            throw WarehouseException.badRequest("InvalidGender", "Gender cannot be null!");
        }
        if (employeeRepository.existsByEmail(employeeCreateDTO.getEmail())){
            throw WarehouseException.badRequest("EmailExisted", "Email is already taken!");
        }
        if (!BooleanChecking.isLowerCaseAlphanumeric(employeeCreateDTO.getEmail())){
            throw WarehouseException.badRequest("WrongFormatEmail", "Email must contains only lowercase alphabetical characters, digits and dots without any whitespaces.");
        }
        if (employeeCreateDTO.getPhone() == null || employeeCreateDTO.getPhone().isEmpty() || employeeCreateDTO.getPhone().isBlank()){
            throw WarehouseException.badRequest("InvalidPhone", "Phone cannot be null!");
        }
        if (!BooleanChecking.isPhoneFormat(employeeCreateDTO.getPhone())){
            throw WarehouseException.badRequest("WrongFormatPhoneNumber", "Phone number must follow rules. For example: 123-4567-89");
        }
        if (employeeCreateDTO.getHireDate() == null){
            throw WarehouseException.badRequest("InvalidHireDate", "Hire date cannot be null!");
        }
        if (employeeCreateDTO.getSalary() < 0){
            throw WarehouseException.badRequest("InvalidSalary", "Salary cannot below 0!");
        }
        if (employeeCreateDTO.getRole() == null || employeeCreateDTO.getRole().isBlank() || employeeCreateDTO.getRole().isEmpty()){
            throw WarehouseException.badRequest("InvalidRole", "Role cannot be null!");
        }
        if (!employeeCreateDTO.getRole().equals(Role.ROLE_WAREHOUSE_STAFF) && !employeeCreateDTO.getRole().equals(Role.ROLE_ADMIN) && !employeeCreateDTO.getRole().equals(Role.ROLE_USER)){
            throw WarehouseException.badRequest("InvalidRole", "Role must be ROLE_WAREHOUSE_STAFF or ROLE_ADMIN or ROLE_USER");
        }
        if (employeeCreateDTO.getEmployeePosition() == null || employeeCreateDTO.getEmployeePosition().isBlank() || employeeCreateDTO.getEmployeePosition().isEmpty()) {
            throw WarehouseException.badRequest("InvalidPosition", "Employee position cannot be null!");
        }
        if (employeeCreateDTO.getPassword() == null || employeeCreateDTO.getPassword().isBlank() || employeeCreateDTO.getPassword().isEmpty()) {
            throw WarehouseException.badRequest("InvalidPassword", "Password cannot be null!");
        }
        if (employeeRepository.existsByUsername(employeeCreateDTO.getUsername())){
            throw WarehouseException.badRequest("UsernameExisted", "Username is already taken!");
        }
        if (!BooleanChecking.isAlphanumericAndNoWhiteSpace(employeeCreateDTO.getUsername())){
            throw WarehouseException.badRequest("WrongFormatUsername", "Username must contains only alphabetical characters, digits and dots without any whitespaces.");
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        log.info("delete employee by id {}", employeeId);
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDTO> findByFirstName(String firstName) {
        List<Employee> employee = employeeRepository.findByFirstName(firstName);
        if (firstName == null || firstName.trim().isBlank() || firstName.isEmpty()){
            throw WarehouseException.badRequest("InvalidName", "First name cannot be null!");
        }
        if (employee.isEmpty()){
            throw WarehouseException.EmployeeNotFound();
        }
        return employeeMapper.toDtos(employee);
    }
}
