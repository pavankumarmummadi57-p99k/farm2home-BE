package com.pavankumar.farm2home.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

}