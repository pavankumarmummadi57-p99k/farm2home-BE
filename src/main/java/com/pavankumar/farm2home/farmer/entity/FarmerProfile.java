package com.pavankumar.farm2home.farmer.entity;

import com.pavankumar.farm2home.common.entity.BaseEntity;
import com.pavankumar.farm2home.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "farmer_profiles")
public class FarmerProfile extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "farm_name", nullable = false, length = 100)
    private String farmName;

    @Column(name = "farm_address", nullable = false, length = 255)
    private String farmAddress;

    @Column(nullable = false, length = 100)
    private String village;

    @Column(nullable = false, length = 100)
    private String mandal;

    @Column(nullable = false, length = 100)
    private String district;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 6)
    private String pincode;

}