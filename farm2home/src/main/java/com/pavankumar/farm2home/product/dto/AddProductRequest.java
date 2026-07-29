package com.pavankumar.farm2home.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest {

    @NotNull(message = "Category Id is required.")
    private Long categoryId;

    @NotBlank(message = "Product Name is required.")
    private String productName;

    private String description;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotBlank(message = "Unit is required.")
    private String unit;

    @NotNull(message = "Available Quantity is required.")
    @DecimalMin(value = "0.01", message = "Available Quantity must be greater than zero.")
    private BigDecimal availableQuantity;

    @NotNull(message = "Minimum Order Quantity is required.")
    @DecimalMin(value = "0.01", message = "Minimum Order Quantity must be greater than zero.")
    private BigDecimal minimumOrderQuantity;
}