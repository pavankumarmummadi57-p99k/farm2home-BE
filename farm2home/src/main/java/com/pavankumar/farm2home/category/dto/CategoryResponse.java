package com.pavankumar.farm2home.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {

    private Long id;

    private String name;

    private String description;

    private Boolean isActive;

}