package com.pavankumar.farm2home.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pavankumar.farm2home.category.dto.AddCategoryRequest;
import com.pavankumar.farm2home.category.dto.CategoryResponse;
import com.pavankumar.farm2home.category.entity.Category;
import com.pavankumar.farm2home.category.repository.CategoryRepository;
import com.pavankumar.farm2home.category.service.CategoryService;
import com.pavankumar.farm2home.common.dto.ApiResponse;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse addCategory(AddCategoryRequest request) {

        if (categoryRepository.findByName(request.getName()).isPresent()) {

            return new ApiResponse(
                    false,
                    "Category already exists.",
                    null);
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setIsActive(true);

        categoryRepository.save(category);

        return new ApiResponse(
                true,
                "Category added successfully.",
                null);
    }

    @Override
    public ApiResponse getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        List<CategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {

            CategoryResponse response = new CategoryResponse();

            response.setId(category.getId());
            response.setName(category.getName());
            response.setDescription(category.getDescription());
            response.setIsActive(category.getIsActive());

            responseList.add(response);
        }

        return new ApiResponse(
                true,
                "Categories fetched successfully.",
                responseList);
    }
}