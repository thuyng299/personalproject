package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.ProductCreateDTO;
import com.nonit.personalproject.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
@RequestMapping(value = "/products")
public interface ProductAPI {
    @GetMapping
    ResponseEntity<List<ProductDTO>> getAllProduct();

    @GetMapping("/{productId}") // localhost:8080/products/3
    ResponseEntity<ProductDTO> findProductById(@PathVariable("productId") Long productId);

    @PostMapping("/{areaId}")
    ResponseEntity<ProductDTO> createProduct(@PathVariable("areaId") Long areaId,
                                             @RequestBody ProductCreateDTO productCreateDTO);

    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId);

    @PutMapping("/{productId}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") Long productId,
                                             @RequestBody ProductCreateDTO productCreateDTO);
}
