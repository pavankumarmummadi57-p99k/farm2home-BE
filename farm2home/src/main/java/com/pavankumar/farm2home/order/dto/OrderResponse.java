//package com.pavankumar.farm2home.order.dto;
//import java.math.BigDecimal;
//import lombok.Getter;
//import lombok.Setter; 
//@Getter
//@Setter 
//public class OrderResponse {
//	private Long orderId; 
//	private String productName;
//	private String farmerName;
//	private BigDecimal quantity; 
//	private BigDecimal pricePerUnit; 
//	private BigDecimal totalAmount; 
//	private String orderStatus; 
//}

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

    private String customerName;

    private String customerPhoneNumber;

    private BigDecimal quantity;

    private BigDecimal pricePerUnit;

    private BigDecimal totalAmount;

    private String orderStatus;

    private String rejectionReason;
    // Order placed date/time
    private String orderDate;
    
    
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryVillage;
    private String deliveryMandal;
    private String deliveryDistrict;
    private String deliveryState;
    private String deliveryPincode;
    private String deliveryLandmark;
    
}