package com.pavankumar.farm2home.product.entity;

import java.math.BigDecimal;

import com.pavankumar.farm2home.category.entity.Category;
import com.pavankumar.farm2home.common.entity.BaseEntity;
import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.product.enums.Unit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_profile_id", nullable = false)
    private FarmerProfile farmerProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "product_name", nullable = false, length = 150)
    private String productName;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unit unit;

    @Column(name = "available_quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal availableQuantity;
    
    @Column(name = "minimum_order_quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal minimumOrderQuantity;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;
}