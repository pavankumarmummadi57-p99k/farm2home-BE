package com.pavankumar.farm2home.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressRequest {

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String village;

    @NotBlank
    private String mandal;

    @NotBlank
    private String district;

    @NotBlank
    private String state;

    @NotBlank
    @Pattern(regexp = "^\\d{6}$")
    private String pincode;

    private String landmark;
}