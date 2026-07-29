package com.pavankumar.farm2home.farmer.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.common.file.FileStorageService;
import com.pavankumar.farm2home.farmer.entity.FarmerProfile;
import com.pavankumar.farm2home.farmer.entity.FarmerVerification;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;
import com.pavankumar.farm2home.farmer.repository.FarmerProfileRepository;
import com.pavankumar.farm2home.farmer.repository.FarmerVerificationRepository;
import com.pavankumar.farm2home.farmer.service.VerificationService;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final FarmerProfileRepository farmerProfileRepository;
    private final FarmerVerificationRepository verificationRepository;
    private final FileStorageService fileStorageService;

    public VerificationServiceImpl(
            FarmerProfileRepository farmerProfileRepository,
            FarmerVerificationRepository verificationRepository,
            FileStorageService fileStorageService) {

        this.farmerProfileRepository = farmerProfileRepository;
        this.verificationRepository = verificationRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ApiResponse uploadFarmPhoto(MultipartFile file, String phoneNumber) {

        Optional<FarmerProfile> optionalFarmer =
                farmerProfileRepository.findByPhoneNumber(phoneNumber);

        if (optionalFarmer.isEmpty()) {
            return new ApiResponse(false, "Farmer profile not found.", null);
        }

        FarmerProfile farmer = optionalFarmer.get();

        FarmerVerification verification =
                verificationRepository.findByFarmerProfileId(farmer.getId())
                        .orElse(new FarmerVerification());

        verification.setFarmerProfile(farmer);

        String imagePath =
                fileStorageService.storeFile(file, "farmer/farm");

        verification.setFarmPhotoPath(imagePath);
        verification.setVerificationStatus(VerificationStatus.PENDING);

        verificationRepository.save(verification);

        return new ApiResponse(true,
                "Farm photo uploaded successfully.",
                imagePath);
    }

    @Override
    public ApiResponse uploadGovernmentId(
            MultipartFile frontImage,
            MultipartFile backImage,
            String phoneNumber) {

        Optional<FarmerProfile> optionalFarmer =
                farmerProfileRepository.findByPhoneNumber(phoneNumber);

        if (optionalFarmer.isEmpty()) {
            return new ApiResponse(false, "Farmer profile not found.", null);
        }

        FarmerProfile farmer = optionalFarmer.get();

        Optional<FarmerVerification> optionalVerification =
                verificationRepository.findByFarmerProfileId(farmer.getId());

        if (optionalVerification.isEmpty()) {
            return new ApiResponse(false,
                    "Please upload farm photo first.",
                    null);
        }

        FarmerVerification verification = optionalVerification.get();

        String frontPath =
                fileStorageService.storeFile(
                        frontImage,
                        "farmer/government-id/front");

        String backPath =
                fileStorageService.storeFile(
                        backImage,
                        "farmer/government-id/back");

        verification.setGovernmentIdFrontPath(frontPath);
        verification.setGovernmentIdBackPath(backPath);

        verificationRepository.save(verification);

        return new ApiResponse(true,
                "Government ID uploaded successfully.",
                null);
    }

    @Override
    public ApiResponse getVerificationStatus(String phoneNumber) {

        Optional<FarmerProfile> optionalFarmer =
                farmerProfileRepository.findByPhoneNumber(phoneNumber);

        if (optionalFarmer.isEmpty()) {
            return new ApiResponse(false,
                    "Farmer profile not found.",
                    null);
        }

        FarmerProfile farmer = optionalFarmer.get();

        Optional<FarmerVerification> optionalVerification =
                verificationRepository.findByFarmerProfileId(farmer.getId());

        if (optionalVerification.isEmpty()) {
            return new ApiResponse(false,
                    "Verification details not found.",
                    null);
        }

        FarmerVerification verification = optionalVerification.get();

        com.pavankumar.farm2home.farmer.dto.VerificationResponse response =
                new com.pavankumar.farm2home.farmer.dto.VerificationResponse();

        response.setFarmPhotoPath(verification.getFarmPhotoPath());
        response.setGovernmentIdFrontPath(verification.getGovernmentIdFrontPath());
        response.setGovernmentIdBackPath(verification.getGovernmentIdBackPath());
        response.setVerificationStatus(verification.getVerificationStatus());
        response.setRemarks(verification.getRemarks());

        return new ApiResponse(
                true,
                "Verification status fetched successfully.",
                response);
    }
}