package com.pavankumar.farm2home.admin.dto;

import com.pavankumar.farm2home.farmer.enums.VerificationStatus;
import com.pavankumar.farm2home.farmer.enums.GovernmentIdType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmerDetailsResponse {

    private Long farmerId;

    private String farmerName;

    private String phoneNumber;

    private String farmName;

    private String farmAddress;

    private String village;

    private String mandal;

    private String district;

    private String state;

    private String pincode;

    private Double farmArea;

    private GovernmentIdType governmentIdType;

    private String governmentIdNumber;

    private String farmPhotoPath;

    private String governmentIdFrontPath;

    private String governmentIdBackPath;

    private VerificationStatus verificationStatus;

    private String remarks;

}