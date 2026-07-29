package com.pavankumar.farm2home.farmer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.farmer.dto.FarmerRegistrationRequest;
import com.pavankumar.farm2home.farmer.dto.UpdateFarmerProfileRequest;
import com.pavankumar.farm2home.farmer.service.FarmerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/farmer")
@Validated
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerFarmer(
            @Valid @RequestBody FarmerRegistrationRequest request) {

        ApiResponse response = farmerService.registerFarmer(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<ApiResponse> getMyProfile() {

        ApiResponse response = farmerService.getMyProfile();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<ApiResponse> updateMyProfile(
            @Valid @RequestBody UpdateFarmerProfileRequest request) {

        ApiResponse response = farmerService.updateMyProfile(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}