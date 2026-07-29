package com.pavankumar.farm2home.farmer.dto;

import com.pavankumar.farm2home.farmer.enums.GovernmentIdType;
import com.pavankumar.farm2home.farmer.enums.VerificationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmerResponse {

    private Long id;

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

    private VerificationStatus verificationStatus;

}