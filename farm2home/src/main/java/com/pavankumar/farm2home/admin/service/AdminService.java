package com.pavankumar.farm2home.admin.service;

import com.pavankumar.farm2home.admin.dto.RejectFarmerRequest;
import com.pavankumar.farm2home.common.dto.ApiResponse;

public interface AdminService {

    ApiResponse getPendingFarmers();

    ApiResponse getFarmerDetails(Long farmerId);

    ApiResponse approveFarmer(Long farmerId);

    ApiResponse rejectFarmer(Long farmerId, RejectFarmerRequest request);

}