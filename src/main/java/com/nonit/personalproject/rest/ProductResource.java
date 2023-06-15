package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.ProductCreateDTO;
import com.nonit.personalproject.dto.ProductDTO;
import com.nonit.personalproject.serviceimpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductResource implements ProductAPI{
    private final ProductServiceImpl productServiceImpl;

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        return ResponseEntity.ok(productServiceImpl.getAllProduct());
    }

    @Override
    public ResponseEntity<ProductDTO> findProductById(Long productId) {
        return ResponseEntity.ok(productServiceImpl.findProductById(productId));
    }

    @Override
    public ResponseEntity<ProductDTO> createProduct(Long areaId, ProductCreateDTO productCreateDTO) {
        ProductDTO createdProductDTO = productServiceImpl.createProduct(areaId, productCreateDTO);
        return ResponseEntity.created(URI.create("/products" + createdProductDTO.getId())).body(createdProductDTO);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        productServiceImpl.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(Long productId, ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok().body(productServiceImpl.updateProduct(productId, productCreateDTO));
    }
}
