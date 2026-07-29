package com.pavankumar.farm2home.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pavankumar.farm2home.category.dto.AddCategoryRequest;
import com.pavankumar.farm2home.category.service.CategoryService;
import com.pavankumar.farm2home.common.dto.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(
            @Valid @RequestBody AddCategoryRequest request) {

        ApiResponse response = categoryService.addCategory(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {

        ApiResponse response = categoryService.getAllCategories();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}