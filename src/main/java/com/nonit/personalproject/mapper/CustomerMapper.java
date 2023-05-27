package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.CustomerDTO;
import com.nonit.personalproject.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toDto (Customer customer);
    List<CustomerDTO> toDtos (List<Customer> customers);
}
