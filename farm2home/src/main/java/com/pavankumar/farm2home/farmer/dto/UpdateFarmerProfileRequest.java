package com.pavankumar.farm2home.farmer.dto;

import com.pavankumar.farm2home.farmer.enums.GovernmentIdType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFarmerProfileRequest {

    @NotBlank(message = "Farmer name is required")
    private String farmerName;

    @NotBlank(message = "Farm name is required")
    private String farmName;

    @NotBlank(message = "Farm address is required")
    private String farmAddress;

    @NotBlank
    private String village;

    @NotBlank
    private String mandal;

    @NotBlank
    private String district;

    @NotBlank
    private String state;

    @Pattern(regexp = "^\\d{6}$", message = "Invalid pincode")
    private String pincode;

    @NotNull
    private Double farmArea;

    @NotNull
    private GovernmentIdType governmentIdType;

    @NotBlank
    private String governmentIdNumber;

}