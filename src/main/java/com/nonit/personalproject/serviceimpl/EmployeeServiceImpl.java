package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.EmployeeCreateDTO;
import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.dto.EmployeeUpdateDTO;
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
                .role(employeeCreateDTO.getRole())
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
        if (employeeRepository.existsByPhone(employeeCreateDTO.getPhone())){
            throw WarehouseException.badRequest("PhoneExisted", "Phone is already taken!");
        }
        if (employeeCreateDTO.getHireDate() == null){
            throw WarehouseException.badRequest("InvalidHireDate", "Hire date cannot be null!");
        }
        if (employeeCreateDTO.getSalary() < 0){
            throw WarehouseException.badRequest("InvalidSalary", "Salary cannot below 0!");
        }
        if (employeeCreateDTO.getRole() == null || String.valueOf(employeeCreateDTO.getRole()).isBlank()){
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

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeUpdateDTO employeeUpdateDTO) {
        log.info("update employee by id {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(WarehouseException::EmployeeNotFound);

        if (employeeUpdateDTO.getFirstName() != null) {
            if (!BooleanChecking.isAlpha(employeeUpdateDTO.getFirstName())) {
                throw WarehouseException.badRequest("WrongFormatFirstName", "First name must contains only alphabetical characters.");
            }
        }
        if(employeeUpdateDTO.getLastName() != null) {
            if (!BooleanChecking.isAlpha(employeeUpdateDTO.getLastName())) {
                throw WarehouseException.badRequest("WrongFormatLastName", "Last name must contains only alphabetical characters.");
            }
        }
        if(employeeUpdateDTO.getPhone() != null) {
            if (!BooleanChecking.isPhoneFormat(employeeUpdateDTO.getPhone())) {
                throw WarehouseException.badRequest("WrongFormatPhoneNumber", "Phone number must follow rules. For example: 123-4567-89");
            }
        }

        if (employeeRepository.existsByPhone(employeeUpdateDTO.getPhone())){
            throw WarehouseException.badRequest("PhoneExisted", "Phone already exists!");
        }

        if(employeeUpdateDTO.getSalary() != null) {
            if (employeeUpdateDTO.getSalary() < 0) {
                throw WarehouseException.badRequest("InvalidSalary", "Salary cannot below 0!");
            }
        }

        if(employeeUpdateDTO.getEmployeePosition() != null) {
            if (!employeeUpdateDTO.getRole().equals(Role.ROLE_WAREHOUSE_STAFF) && !employeeUpdateDTO.getRole().equals(Role.ROLE_ADMIN) && !employeeUpdateDTO.getRole().equals(Role.ROLE_USER)) {
                throw WarehouseException.badRequest("InvalidRole", "Role must be ROLE_WAREHOUSE_STAFF or ROLE_ADMIN or ROLE_USER");
            }
        }

        employee.setFirstName(employeeUpdateDTO.getFirstName() == null ? employee.getFirstName() : employeeUpdateDTO.getFirstName());
        employee.setLastName(employeeUpdateDTO.getLastName() == null ? employee.getLastName() : employeeUpdateDTO.getLastName());
        employee.setGender(employeeUpdateDTO.getGender() == null ? employee.getGender() : employeeUpdateDTO.getGender());
        employee.setPhone(employeeUpdateDTO.getPhone() == null ? employee.getPhone() : employeeUpdateDTO.getPhone());
        employee.setAddress(employeeUpdateDTO.getAddress() == null ? employee.getAddress() : employeeUpdateDTO.getAddress());
        employee.setSalary(employeeUpdateDTO.getSalary() == null ? employee.getSalary() : employeeUpdateDTO.getSalary());
        employee.setEmployeePosition(employeeUpdateDTO.getEmployeePosition() == null ? employee.getEmployeePosition() : employeeUpdateDTO.getEmployeePosition());
        employee.setEmployeeStatus(employeeUpdateDTO.getEmployeeStatus() == null ? employee.getEmployeeStatus() : employeeUpdateDTO.getEmployeeStatus());
        employee.setRole(employeeUpdateDTO.getRole()==null ? employee.getRole() : Role.valueOf(employeeUpdateDTO.getRole()));
        employee.setWarehouseArea(employeeUpdateDTO.getAreaId() == null ? employee.getWarehouseArea() : warehouseAreaRepository.findById(employeeUpdateDTO.getAreaId())
                .orElseThrow(WarehouseException::WarehouseAreaNotFound));

        employeeMapper.mapFromDto(employeeUpdateDTO, employee);
        return employeeMapper.toDto(employeeRepository.save(employee));

    }
}
