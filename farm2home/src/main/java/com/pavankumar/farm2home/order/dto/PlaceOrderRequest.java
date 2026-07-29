package com.pavankumar.farm2home.order.dto;
import java.math.BigDecimal; 
import lombok.Getter;
import lombok.Setter;
@Getter 
@Setter 
public class PlaceOrderRequest { 
	private Long productId;
	private BigDecimal quantity;
}