package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    @Mapping(source = "role", target = "role")
    EmployeeDTO toDto (Employee employee);
    List<EmployeeDTO> toDtos (List<Employee> employees);
}
