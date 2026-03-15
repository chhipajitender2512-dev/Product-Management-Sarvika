package com.sarvika.productmanagement.domain;

import com.sarvika.productmanagement.domain.reponse.ProductResponse;
import com.sarvika.productmanagement.domain.request.ProductRequest;
import com.sarvika.productmanagement.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static void updateEntity(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
    }
}
