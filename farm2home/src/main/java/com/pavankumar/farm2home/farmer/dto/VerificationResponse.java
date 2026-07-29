package com.pavankumar.farm2home.farmer.dto;

import com.pavankumar.farm2home.farmer.enums.VerificationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationResponse {

    private String farmPhotoPath;

    private String governmentIdFrontPath;

    private String governmentIdBackPath;

    private VerificationStatus verificationStatus;

    private String remarks;

}