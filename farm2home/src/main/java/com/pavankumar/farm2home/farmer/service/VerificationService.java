package com.pavankumar.farm2home.farmer.service;

import org.springframework.web.multipart.MultipartFile;

import com.pavankumar.farm2home.common.dto.ApiResponse;

public interface VerificationService {

    ApiResponse uploadFarmPhoto(MultipartFile file, String phoneNumber);

    ApiResponse uploadGovernmentId(
            MultipartFile frontImage,
            MultipartFile backImage,
            String phoneNumber);

    ApiResponse getVerificationStatus(String phoneNumber);

}