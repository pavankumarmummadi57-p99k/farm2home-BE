package com.pavankumar.farm2home.farmer.entity;

import java.time.LocalDateTime;

import com.pavankumar.farm2home.common.entity.BaseEntity;
import com.pavankumar.farm2home.farmer.enums.GovernmentIdType;
import com.pavankumar.farm2home.user.enums.VerificationStatus;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "government_id_type", nullable = false)
    private GovernmentIdType governmentIdType;

    @Column(name = "government_id_number", nullable = false, length = 30)
    private String governmentIdNumber;

    @Column(name = "government_id_image_path", nullable = false)
    private String governmentIdImagePath;

    @Column(name = "farm_photo_path", nullable = false)
    private String farmPhotoPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private VerificationStatus verificationStatus;

    @Column(name = "admin_remarks", length = 500)
    private String adminRemarks;

    @Column(name = "verified_date")
    private LocalDateTime verifiedDate;
}