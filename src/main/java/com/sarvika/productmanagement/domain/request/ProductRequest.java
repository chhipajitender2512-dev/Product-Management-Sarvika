package com.sarvika.productmanagement.domain.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NonNull
    @NotBlank(message = "Name should not be blank.")
    private String name;
    private String description;
    @NonNull
    @Positive
    @Digits(integer = 10, fraction = 2, message = "Price must not exceed 10 digits and 2 decimal places.")
    private BigDecimal price;
}
