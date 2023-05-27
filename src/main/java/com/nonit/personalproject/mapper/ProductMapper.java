package com.nonit.personalproject.mapper;

import com.nonit.personalproject.dto.ProductDTO;
import com.nonit.personalproject.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(source = "productCategory", target = "productCategory")
    ProductDTO toDto (Product product);
    List<ProductDTO> toDtos (List<Product> products);
}
