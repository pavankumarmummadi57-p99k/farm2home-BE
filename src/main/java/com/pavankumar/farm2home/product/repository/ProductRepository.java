package com.pavankumar.farm2home.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.category.entity.Category;
import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);

    List<Product> findByFarmerProfile(FarmerProfile farmerProfile);

    List<Product> findByIsAvailableTrue();

}