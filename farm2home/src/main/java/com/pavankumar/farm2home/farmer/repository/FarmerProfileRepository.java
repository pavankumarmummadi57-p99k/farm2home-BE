package com.pavankumar.farm2home.farmer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.user.entity.User;

public interface FarmerProfileRepository extends JpaRepository<FarmerProfile, Long> {

    Optional<FarmerProfile> findByUser(User user);

}