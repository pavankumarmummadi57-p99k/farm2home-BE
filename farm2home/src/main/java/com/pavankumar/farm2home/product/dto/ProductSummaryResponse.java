package com.pavankumar.farm2home.product.dto; 
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter; 
@Getter 
@Setter
public class ProductSummaryResponse { 
	private Long productId; 
	private String productName; 
	private String description;
	private BigDecimal price; 
	private String unit; 
	private BigDecimal availableQuantity; 
	private Boolean isAvailable; 
	private List<String> imagePaths; 
	}