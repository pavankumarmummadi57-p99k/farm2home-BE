package com.pavankumar.farm2home.category.service;

import com.pavankumar.farm2home.category.dto.AddCategoryRequest;
import com.pavankumar.farm2home.common.dto.ApiResponse;

public interface CategoryService {

    ApiResponse addCategory(AddCategoryRequest request);

    ApiResponse getAllCategories();

}