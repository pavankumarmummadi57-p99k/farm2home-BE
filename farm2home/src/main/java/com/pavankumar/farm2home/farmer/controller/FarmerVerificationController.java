package com.pavankumar.farm2home.farmer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.farmer.service.VerificationService;

@RestController
@RequestMapping("/api/farmer/verification")
public class FarmerVerificationController {

    private final VerificationService verificationService;

    public FarmerVerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/upload-farm-photo")
    public ResponseEntity<ApiResponse> uploadFarmPhoto(
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {

        String phoneNumber = authentication.getName();

        ApiResponse response =
                verificationService.uploadFarmPhoto(file, phoneNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/upload-government-id")
    public ResponseEntity<ApiResponse> uploadGovernmentId(
            @RequestParam("frontImage") MultipartFile frontImage,
            @RequestParam("backImage") MultipartFile backImage,
            Authentication authentication) {

        String phoneNumber = authentication.getName();

        ApiResponse response =
                verificationService.uploadGovernmentId(
                        frontImage,
                        backImage,
                        phoneNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse> getVerificationStatus(
            Authentication authentication) {

        String phoneNumber = authentication.getName();

        ApiResponse response =
                verificationService.getVerificationStatus(phoneNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}