package com.pavankumar.farm2home.farmer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.farmer.entity.FarmerProfile;

public interface FarmerProfileRepository extends JpaRepository<FarmerProfile, Long> {

    Optional<FarmerProfile> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<FarmerProfile> findByUserPhoneNumber(String phoneNumber);

}