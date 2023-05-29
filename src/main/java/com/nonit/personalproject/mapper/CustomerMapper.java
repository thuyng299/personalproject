package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.CustomerCreateDTO;
import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.entity.Customer;
import com.nonit.personalproject.entity.Supplier;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toDto (Customer customer);
    List<CustomerDTO> toDtos (List<Customer> customers);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapFromDto (CustomerCreateDTO customerCreateDTO, @MappingTarget Customer customer);
}
