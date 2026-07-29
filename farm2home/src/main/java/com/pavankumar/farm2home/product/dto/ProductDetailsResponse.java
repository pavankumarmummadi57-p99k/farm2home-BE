package com.pavankumar.farm2home.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailsResponse {

    private Long productId;

    private String farmerName;

    private String categoryName;

    private String productName;

    private String description;

    private BigDecimal price;

    private String unit;

    private BigDecimal availableQuantity;

    private BigDecimal minimumOrderQuantity;

    private Boolean isAvailable;

    private List<String> imagePaths;
}