package com.pavankumar.farm2home.farmer.service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.farmer.dto.FarmerRegistrationRequest;
import com.pavankumar.farm2home.farmer.dto.UpdateFarmerProfileRequest;

public interface FarmerService {

    ApiResponse registerFarmer(FarmerRegistrationRequest request);

    ApiResponse getMyProfile();

    ApiResponse updateMyProfile(UpdateFarmerProfileRequest request);

}