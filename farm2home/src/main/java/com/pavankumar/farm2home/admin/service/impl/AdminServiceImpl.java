package com.pavankumar.farm2home.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pavankumar.farm2home.admin.dto.PendingFarmerResponse;
import com.pavankumar.farm2home.admin.service.AdminService;
import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.farmer.entity.FarmerVerification;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;
import com.pavankumar.farm2home.farmer.repository.FarmerProfileRepository;
import com.pavankumar.farm2home.farmer.repository.FarmerVerificationRepository;
import java.util.Optional;
import com.pavankumar.farm2home.admin.dto.FarmerDetailsResponse;
import com.pavankumar.farm2home.admin.dto.RejectFarmerRequest;

@Service
public class AdminServiceImpl implements AdminService {

    private final FarmerVerificationRepository verificationRepository;
    private final FarmerProfileRepository farmerProfileRepository;

    public AdminServiceImpl(
            FarmerVerificationRepository verificationRepository,
            FarmerProfileRepository farmerProfileRepository) {

        this.verificationRepository = verificationRepository;
        this.farmerProfileRepository = farmerProfileRepository;
    }

    @Override
    public ApiResponse getPendingFarmers() {

        List<FarmerVerification> verifications =
                verificationRepository.findByVerificationStatus(
                        VerificationStatus.PENDING);

        List<PendingFarmerResponse> responseList = new ArrayList<>();

        for (FarmerVerification verification : verifications) {

            FarmerProfile farmer = verification.getFarmerProfile();

            PendingFarmerResponse response = new PendingFarmerResponse();

            response.setFarmerId(farmer.getId());
            response.setFarmerName(farmer.getFarmerName());
            response.setPhoneNumber(farmer.getPhoneNumber());
            response.setFarmName(farmer.getFarmName());
            response.setVerificationStatus(verification.getVerificationStatus());

            responseList.add(response);
        }

        return new ApiResponse(
                true,
                "Pending farmers fetched successfully.",
                responseList);
    }
    
    @Override
    public ApiResponse getFarmerDetails(Long farmerId) {

        Optional<FarmerProfile> optionalFarmer =
                farmerProfileRepository.findById(farmerId);

        if (optionalFarmer.isEmpty()) {
            return new ApiResponse(false,
                    "Farmer not found.",
                    null);
        }

        FarmerProfile farmer = optionalFarmer.get();

        Optional<FarmerVerification> optionalVerification =
                verificationRepository.findByFarmerProfileId(farmerId);

        if (optionalVerification.isEmpty()) {
            return new ApiResponse(false,
                    "Verification details not found.",
                    null);
        }

        FarmerVerification verification = optionalVerification.get();

        FarmerDetailsResponse response = new FarmerDetailsResponse();

        response.setFarmerId(farmer.getId());
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

        response.setFarmPhotoPath(verification.getFarmPhotoPath());
        response.setGovernmentIdFrontPath(verification.getGovernmentIdFrontPath());
        response.setGovernmentIdBackPath(verification.getGovernmentIdBackPath());
        response.setVerificationStatus(verification.getVerificationStatus());
        response.setRemarks(verification.getRemarks());

        return new ApiResponse(
                true,
                "Farmer details fetched successfully.",
                response);
    }
    
    @Override
    public ApiResponse approveFarmer(Long farmerId) {

        Optional<FarmerVerification> optionalVerification =
                verificationRepository.findByFarmerProfileId(farmerId);

        if (optionalVerification.isEmpty()) {
            return new ApiResponse(
                    false,
                    "Verification details not found.",
                    null);
        }

        FarmerVerification verification = optionalVerification.get();

        verification.setVerificationStatus(VerificationStatus.APPROVED);
        verification.setRemarks("Approved by Admin");

        verificationRepository.save(verification);

        return new ApiResponse(
                true,
                "Farmer approved successfully.",
                null);
    }
    
    @Override
    public ApiResponse rejectFarmer(Long farmerId, RejectFarmerRequest request) {

        Optional<FarmerVerification> optionalVerification =
                verificationRepository.findByFarmerProfileId(farmerId);

        if (optionalVerification.isEmpty()) {
            return new ApiResponse(
                    false,
                    "Verification details not found.",
                    null);
        }

        FarmerVerification verification = optionalVerification.get();

        verification.setVerificationStatus(VerificationStatus.REJECTED);
        verification.setRemarks(request.getRemarks());

        verificationRepository.save(verification);

        return new ApiResponse(
                true,
                "Farmer rejected successfully.",
                null);
    }

}