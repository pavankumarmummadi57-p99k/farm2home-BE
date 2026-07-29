package com.pavankumar.farm2home.farmer.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.farmer.dto.FarmerRegistrationRequest;
import com.pavankumar.farm2home.farmer.dto.FarmerResponse;
import com.pavankumar.farm2home.farmer.dto.UpdateFarmerProfileRequest;
import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;
import com.pavankumar.farm2home.farmer.repository.FarmerProfileRepository;
import com.pavankumar.farm2home.farmer.service.FarmerService;
import com.pavankumar.farm2home.user.entity.User;
import com.pavankumar.farm2home.user.repository.UserRepository;

@Service
public class FarmerServiceImpl implements FarmerService {

    private final FarmerProfileRepository farmerRepository;
    private final UserRepository userRepository;

    public FarmerServiceImpl(FarmerProfileRepository farmerRepository,
                             UserRepository userRepository) {

        this.farmerRepository = farmerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse registerFarmer(FarmerRegistrationRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNumber = authentication.getName();

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElse(null);

        if (user == null) {
            return new ApiResponse(false, "User not found.", null);
        }

        if (farmerRepository.existsByPhoneNumber(phoneNumber)) {
            return new ApiResponse(false,
                    "Farmer profile already exists.",
                    null);
        }

        FarmerProfile farmer = new FarmerProfile();

        farmer.setUser(user);
        farmer.setFarmerName(request.getFarmerName());
        farmer.setPhoneNumber(phoneNumber);
        farmer.setFarmName(request.getFarmName());
        farmer.setFarmAddress(request.getFarmAddress());
        farmer.setVillage(request.getVillage());
        farmer.setMandal(request.getMandal());
        farmer.setDistrict(request.getDistrict());
        farmer.setState(request.getState());
        farmer.setPincode(request.getPincode());
        farmer.setFarmArea(request.getFarmArea());
        farmer.setGovernmentIdType(request.getGovernmentIdType());
        farmer.setGovernmentIdNumber(request.getGovernmentIdNumber());
        farmer.setVerificationStatus(VerificationStatus.PENDING);

        farmerRepository.save(farmer);

        return new ApiResponse(true,
                "Farmer registration submitted successfully.",
                null);
    }

    @Override
    public ApiResponse getMyProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNumber = authentication.getName();

        FarmerProfile farmer = farmerRepository
                .findByUserPhoneNumber(phoneNumber)
                .orElse(null);

        if (farmer == null) {
            return new ApiResponse(false,
                    "Farmer profile not found.",
                    null);
        }

        FarmerResponse response = new FarmerResponse();

        response.setId(farmer.getId());
        response.setFarmerName(farmer.getFarmerName());
        response.setPhoneNumber(farmer.getPhoneNumber());
        response.setFarmName(farmer.getFarmName());
        response.setFarmAddress(farmer.getFarmAddress());
        response.setVillage(farmer.getVillage());
        response.setMandal(farmer.getMandal());
        response.setDistrict(farmer.getDistrict());
        response.setState(farmer.getState());
        response.setPincode(farmer.getPincode());
        response.setFarmArea(farmer.getFarmArea());
        response.setGovernmentIdType(farmer.getGovernmentIdType());
        response.setGovernmentIdNumber(farmer.getGovernmentIdNumber());
        response.setVerificationStatus(farmer.getVerificationStatus());

        return new ApiResponse(true,
                "Farmer profile fetched successfully.",
                response);
    }
    @Override
    public ApiResponse updateMyProfile(UpdateFarmerProfileRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNumber = authentication.getName();

        FarmerProfile farmer = farmerRepository
                .findByUserPhoneNumber(phoneNumber)
                .orElse(null);

        if (farmer == null) {
            return new ApiResponse(false,
                    "Farmer profile not found.",
                    null);
        }

        // Update Details
        farmer.setFarmerName(request.getFarmerName());
        farmer.setFarmName(request.getFarmName());
        farmer.setFarmAddress(request.getFarmAddress());
        farmer.setVillage(request.getVillage());
        farmer.setMandal(request.getMandal());
        farmer.setDistrict(request.getDistrict());
        farmer.setState(request.getState());
        farmer.setPincode(request.getPincode());
        farmer.setFarmArea(request.getFarmArea());
        farmer.setGovernmentIdType(request.getGovernmentIdType());
        farmer.setGovernmentIdNumber(request.getGovernmentIdNumber());

        // Reset verification if government ID changed
        farmer.setVerificationStatus(VerificationStatus.PENDING);

        farmerRepository.save(farmer);

        return new ApiResponse(true,
                "Farmer profile updated successfully.",
                null);
    }

}