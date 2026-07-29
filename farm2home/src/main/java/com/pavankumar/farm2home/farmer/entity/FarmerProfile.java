package com.pavankumar.farm2home.farmer.entity;

import com.pavankumar.farm2home.common.entity.BaseEntity;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;
import com.pavankumar.farm2home.user.entity.User;
import com.pavankumar.farm2home.farmer.enums.GovernmentIdType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column(name = "farmer_name", nullable = false, length = 100)
    private String farmerName;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

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

    @Column(name = "farm_area")
    private Double farmArea;

    @Enumerated(EnumType.STRING)
    @Column(name = "government_id_type", nullable = false)
    private GovernmentIdType governmentIdType;

    @Column(name = "government_id_number", nullable = false, unique = true, length = 30)
    private String governmentIdNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private VerificationStatus verificationStatus;

    @Column(length = 500)
    private String remarks;
}