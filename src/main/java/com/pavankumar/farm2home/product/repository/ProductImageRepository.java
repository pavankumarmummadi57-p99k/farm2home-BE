package com.pavankumar.farm2home.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavankumar.farm2home.product.entity.Product;
import com.pavankumar.farm2home.product.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProduct(Product product);

}