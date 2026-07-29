package com.pavankumar.farm2home.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavankumar.farm2home.admin.dto.RejectFarmerRequest;
import com.pavankumar.farm2home.admin.service.AdminService;
import com.pavankumar.farm2home.common.dto.ApiResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/farmers/pending")
    public ResponseEntity<ApiResponse> getPendingFarmers() {

        ApiResponse response = adminService.getPendingFarmers();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<ApiResponse> getFarmerDetails(
            @PathVariable Long farmerId) {

        ApiResponse response =
                adminService.getFarmerDetails(farmerId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/farmer/approve/{farmerId}")
    public ResponseEntity<ApiResponse> approveFarmer(
            @PathVariable Long farmerId) {

        ApiResponse response =
                adminService.approveFarmer(farmerId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/farmer/reject/{farmerId}")
    public ResponseEntity<ApiResponse> rejectFarmer(
            @PathVariable Long farmerId,
            @RequestBody RejectFarmerRequest request) {

        ApiResponse response =
                adminService.rejectFarmer(farmerId, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}