package com.pavankumar.farm2home.product.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadProductImagesResponse {

    private Long productId;

    private List<String> imagePaths;

}