package com.pavankumar.farm2home.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.customer.entity.CustomerAddress;
import com.pavankumar.farm2home.user.entity.User;

public interface CustomerAddressRepository
        extends JpaRepository<CustomerAddress, Long> {

    Optional<CustomerAddress> findByUser(User user);
}