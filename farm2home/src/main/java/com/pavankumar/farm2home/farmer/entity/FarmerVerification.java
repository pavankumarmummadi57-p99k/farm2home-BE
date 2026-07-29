package com.pavankumar.farm2home.farmer.entity;

import com.pavankumar.farm2home.common.entity.BaseEntity;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;

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
@Table(name = "farmer_verifications")
public class FarmerVerification extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_profile_id", nullable = false, unique = true)
    private FarmerProfile farmerProfile;

    @Column(name = "farm_photo_path")
    private String farmPhotoPath;

    @Column(name = "government_id_front_path")
    private String governmentIdFrontPath;

    @Column(name = "government_id_back_path")
    private String governmentIdBackPath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VerificationStatus verificationStatus;

    @Column(length = 500)
    private String remarks;

}