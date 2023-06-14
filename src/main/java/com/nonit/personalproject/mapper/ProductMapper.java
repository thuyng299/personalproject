//package com.nonit.personalproject.mapper;
//
//import com.nonit.personalproject.dto.ProductCreateDTO;
//import com.nonit.personalproject.dto.ProductDTO;
//import com.nonit.personalproject.dto.SupplierCreateDTO;
//import com.nonit.personalproject.entity.Product;
//import com.nonit.personalproject.entity.Supplier;
//import org.mapstruct.*;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
//    @Mapping(source = "productCategory", target = "productCategory")
//    ProductDTO toDto (Product product);
//    List<ProductDTO> toDtos (List<Product> products);
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void mapFromDto (ProductCreateDTO productCreateDTO, @MappingTarget Product product);
//}
