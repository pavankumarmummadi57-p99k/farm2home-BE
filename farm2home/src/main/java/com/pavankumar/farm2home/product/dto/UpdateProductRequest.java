package com.pavankumar.farm2home.product.dto;
import java.math.BigDecimal;
import lombok.Getter; 
import lombok.Setter;
@Getter 
@Setter 
public class UpdateProductRequest {
	private String description; 
	private BigDecimal price;
	private BigDecimal availableQuantity;
	private BigDecimal minimumOrderQuantity; 
	private Boolean isAvailable; 
	}