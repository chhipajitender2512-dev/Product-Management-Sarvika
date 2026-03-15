package com.sarvika.productmanagement.service;

import com.sarvika.productmanagement.domain.reponse.ProductResponse;
import com.sarvika.productmanagement.domain.request.ProductRequest;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse fetchProductById(Long productId);

    List<ProductResponse> fetchAllProducts();

    ProductResponse updateProductById(Long productId, ProductRequest request);

    void deleteProductById(Long productId);
}
