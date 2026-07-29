package com.pavankumar.farm2home.order.dto;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter; 
@Getter
@Setter 
public class OrderResponse {
	private Long orderId; 
	private String productName;
	private String farmerName;
	private BigDecimal quantity; 
	private BigDecimal pricePerUnit; 
	private BigDecimal totalAmount; 
	private String orderStatus; 
}