package com.sarvika.productmanagement.service.impl;

import com.sarvika.productmanagement.domain.ProductMapper;
import com.sarvika.productmanagement.domain.reponse.ProductResponse;
import com.sarvika.productmanagement.domain.request.ProductRequest;
import com.sarvika.productmanagement.entity.Product;
import com.sarvika.productmanagement.exception.ProductNotFoundException;
import com.sarvika.productmanagement.repository.ProductRepository;
import com.sarvika.productmanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = ProductMapper.toEntity(request);
        productRepository.save(product);
        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse fetchProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        return ProductMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> fetchAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse updateProductById(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        ProductMapper.updateEntity(product, request);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        productRepository.delete(product);
    }
}
