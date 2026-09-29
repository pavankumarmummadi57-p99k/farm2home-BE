//package com.pavankumar.farm2home.order.entity;
//import java.math.BigDecimal; 
//import com.pavankumar.farm2home.common.entity.BaseEntity;
//import com.pavankumar.farm2home.order.enums.OrderStatus; 
//import com.pavankumar.farm2home.product.entity.Product; 
//import com.pavankumar.farm2home.user.entity.User;
//import jakarta.persistence.Entity; 
//import jakarta.persistence.EnumType; 
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType; 
//import jakarta.persistence.GeneratedValue; 
//
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn; 
//import jakarta.persistence.ManyToOne; 
//import jakarta.persistence.Table;
//import lombok.Getter; 
//import lombok.Setter; 
//@Getter
//@Setter
//@Entity 
//@Table(name = "orders") 
//public class Order extends BaseEntity {
//	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY) 
//	private Long id;
//	@ManyToOne(fetch = FetchType.LAZY) 
//	@JoinColumn(name = "customer_id") 
//	private User customer;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "product_id")
//	private Product product;
//	private BigDecimal quantity;
//	private BigDecimal pricePerUnit;
//	private BigDecimal totalAmount;
//	@Enumerated(EnumType.STRING)
//	private OrderStatus orderStatus = OrderStatus.PENDING;
//}




package com.pavankumar.farm2home.order.entity;

import java.math.BigDecimal;

import com.pavankumar.farm2home.common.entity.BaseEntity;
import com.pavankumar.farm2home.order.enums.OrderStatus;
import com.pavankumar.farm2home.product.entity.Product;
import com.pavankumar.farm2home.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal quantity;

    private BigDecimal pricePerUnit;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Column(name = "rejection_reason", length = 500)
    private String rejectionReason;
    
    @Column(name = "delivery_address_line1", length = 150)
    private String deliveryAddressLine1;

    @Column(name = "delivery_address_line2", length = 150)
    private String deliveryAddressLine2;

    @Column(name = "delivery_village", length = 100)
    private String deliveryVillage;

    @Column(name = "delivery_mandal", length = 100)
    private String deliveryMandal;

    @Column(name = "delivery_district", length = 100)
    private String deliveryDistrict;

    @Column(name = "delivery_state", length = 100)
    private String deliveryState;

    @Column(name = "delivery_pincode", length = 6)
    private String deliveryPincode;

    @Column(name = "delivery_landmark", length = 150)
    private String deliveryLandmark;
    
}