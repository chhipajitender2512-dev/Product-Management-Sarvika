package com.sarvika.productmanagement.controller;

import com.sarvika.productmanagement.domain.reponse.ProductResponse;
import com.sarvika.productmanagement.domain.request.ProductRequest;
import com.sarvika.productmanagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "APIs for managing products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Create a Product", description = "Creates a new Product")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest createProductRequest) {
        return ResponseEntity.status(201).body(productService.createProduct(createProductRequest));
    }

    @Operation(summary = "Get Product by ID", description = "Returns a single product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(productService.fetchProductById(productId));
    }

    @Operation(summary = "Get All Products", description = "Returns a list of Products")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

    @Operation(summary = "Update a Product", description = "Update an existing Product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable("id") Long productId,
                                                             @RequestBody @Valid ProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updateProductById(productId, updateProductRequest));
    }

    @Operation(summary = "Delete a Product", description = "Deletes a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
