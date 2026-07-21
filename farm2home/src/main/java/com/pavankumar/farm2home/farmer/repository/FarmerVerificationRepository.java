package com.pavankumar.farm2home.farmer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.farmer.entity.FarmerVerification;

public interface FarmerVerificationRepository extends JpaRepository<FarmerVerification, Long> {

    Optional<FarmerVerification> findByFarmerProfile(FarmerProfile farmerProfile);

}