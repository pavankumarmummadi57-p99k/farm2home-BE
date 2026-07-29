package com.pavankumar.farm2home.admin.dto;

import com.pavankumar.farm2home.farmer.enums.VerificationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PendingFarmerResponse {

    private Long farmerId;

    private String farmerName;

    private String phoneNumber;

    private String farmName;

    private VerificationStatus verificationStatus;

}