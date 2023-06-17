package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.EmployeeDTO;
import com.nonit.personalproject.dto.EmployeeUpdateDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.entity.Employee;
import com.nonit.personalproject.entity.Supplier;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    @Mapping(source = "role", target = "role")
    @Mapping(source = "warehouseArea.id", target = "areaId")
    @Mapping(source = "warehouseArea.name", target = "areaName")
    EmployeeDTO toDto (Employee employee);
    List<EmployeeDTO> toDtos (List<Employee> employees);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (EmployeeUpdateDTO employeeUpdateDTO, @MappingTarget Employee employee);
}
