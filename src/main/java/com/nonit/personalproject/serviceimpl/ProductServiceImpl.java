package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.ProductCreateDTO;
import com.nonit.personalproject.dto.ProductDTO;
import com.nonit.personalproject.entity.Product;
import com.nonit.personalproject.entity.ProductCategory;
import com.nonit.personalproject.entity.WarehouseArea;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.ProductMapper;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private  final ProductMapper productMapper = ProductMapper.INSTANCE;
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()){
            throw WarehouseException.ProductNotFound();
        }
        return productMapper.toDtos(products);
    }

    @Override
    public ProductDTO findProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(WarehouseException::ProductNotFound);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO createProduct(Long areaId, ProductCreateDTO productCreateDTO) {
        WarehouseArea warehouseArea = warehouseAreaRepository.findById(areaId).orElseThrow(WarehouseException::WarehouseAreaNotFound);
        if (productCreateDTO.getProductName() == null || productCreateDTO.getProductName().isEmpty() || productCreateDTO.getProductName().isBlank()){
            throw WarehouseException.badRequest("InvalidProductName", "Product name cannot be null!");
        }
        if (productRepository.existsByProductName(productCreateDTO.getProductName())){
            throw WarehouseException.badRequest("ProductNameExisted", "Product name already exists!");
        }
        if (productCreateDTO.getProductCode() == null || productCreateDTO.getProductCode().isBlank() || productCreateDTO.getProductCode().isEmpty()){
            throw WarehouseException.badRequest("InvalidProductCode", "Product code cannot be null!");
        }
        if (productRepository.existsByProductCode(productCreateDTO.getProductCode())){
            throw WarehouseException.badRequest("ProductCodeExisted", "Product code is already taken!");
        }
        if (!productCreateDTO.getProductCategory().equals(ProductCategory.RAW_MATERIALS) && !productCreateDTO.getProductCategory().equals(ProductCategory.FINISHED_GOODS)){
            throw WarehouseException.badRequest("InvalidCategory", "Category must be RAW_MATERIALS or FINISHED_GOODS");
        }
        Product product = Product.builder()
                .productName(productCreateDTO.getProductName())
                .productCode(productCreateDTO.getProductCode())
                .productDescription(productCreateDTO.getProductDescription())
                .productCategory(ProductCategory.valueOf(productCreateDTO.getProductCategory()))
                .warehouseArea(warehouseArea)
                .build();
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        log.info("delete product by id {}", productId);
        productRepository.deleteById(productId);
    }
}
