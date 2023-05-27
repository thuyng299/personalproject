package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.ProductCreateDTO;
import com.nonit.personalproject.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct();
    ProductDTO findProductById (Long productId);
    ProductDTO createProduct (Long areaId, ProductCreateDTO productCreateDTO);
    void deleteProduct (Long productId);
}
