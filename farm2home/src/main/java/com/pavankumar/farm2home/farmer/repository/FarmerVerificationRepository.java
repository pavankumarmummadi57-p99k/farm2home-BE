package com.pavankumar.farm2home.farmer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.farmer.entity.FarmerVerification;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;

public interface FarmerVerificationRepository extends JpaRepository<FarmerVerification, Long> {

    Optional<FarmerVerification> findByFarmerProfileId(Long farmerProfileId);

    Optional<FarmerVerification> findByFarmerProfile(FarmerProfile farmerProfile);

    boolean existsByFarmerProfileId(Long farmerProfileId);

    List<FarmerVerification> findByVerificationStatus(
            VerificationStatus verificationStatus);

}