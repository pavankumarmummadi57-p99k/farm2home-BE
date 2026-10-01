package com.pavankumar.farm2home.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.product.dto.AddProductRequest;
import com.pavankumar.farm2home.product.dto.UpdateProductRequest;
import com.pavankumar.farm2home.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(
            @Valid @RequestBody AddProductRequest request,
            HttpServletRequest httpServletRequest) {
        ApiResponse response = productService.addProduct(request, httpServletRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{productId}/images")
    public ResponseEntity<ApiResponse> uploadProductImages(
            @PathVariable Long productId,
            @RequestParam("images") MultipartFile[] images,
            HttpServletRequest httpServletRequest) {
        ApiResponse response = productService.uploadProductImages(productId, images, httpServletRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my-products")
    public ResponseEntity<ApiResponse> getMyProducts(HttpServletRequest httpServletRequest) {
        ApiResponse response = productService.getMyProducts(httpServletRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductDetails(@PathVariable Long productId) {
        ApiResponse response = productService.getProductDetails(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productId}/farmer-contact")
    public ResponseEntity<ApiResponse> getFarmerContact(@PathVariable Long productId) {
        ApiResponse response = productService.getFarmerContact(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody UpdateProductRequest request,
            HttpServletRequest httpServletRequest) {
        ApiResponse response = productService.updateProduct(productId, request, httpServletRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable Long productId,
            HttpServletRequest httpServletRequest) {
        ApiResponse response = productService.deleteProduct(productId, httpServletRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> browseProducts() {
        ApiResponse response = productService.browseProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> browseProductsByCategory(@PathVariable Long categoryId) {
        ApiResponse response = productService.browseProductsByCategory(categoryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchProducts(@RequestParam String keyword) {
        ApiResponse response = productService.searchProducts(keyword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
