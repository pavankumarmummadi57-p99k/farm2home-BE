package com.pavankumar.farm2home.farmer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationRequest {

    @NotBlank(message = "Government ID Front Image is required")
    private String governmentIdFrontPath;

    private String governmentIdBackPath;

    @NotBlank(message = "Farm Photo is required")
    private String farmPhotoPath;

}