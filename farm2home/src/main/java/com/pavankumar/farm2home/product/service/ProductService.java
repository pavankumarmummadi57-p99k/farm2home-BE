package com.pavankumar.farm2home.product.service;

import org.springframework.web.multipart.MultipartFile;
import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.product.dto.AddProductRequest;
import com.pavankumar.farm2home.product.dto.UpdateProductRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
    ApiResponse addProduct(AddProductRequest request, HttpServletRequest httpServletRequest);
    ApiResponse uploadProductImages(Long productId, MultipartFile[] images, HttpServletRequest httpServletRequest);
    ApiResponse getMyProducts(HttpServletRequest httpServletRequest);
    ApiResponse getProductDetails(Long productId);
    ApiResponse getFarmerContact(Long productId);
    ApiResponse updateProduct(Long productId, UpdateProductRequest request, HttpServletRequest httpServletRequest);
    ApiResponse deleteProduct(Long productId, HttpServletRequest httpServletRequest);
    ApiResponse browseProducts();
    ApiResponse browseProductsByCategory(Long categoryId);
    ApiResponse searchProducts(String keyword);
}
