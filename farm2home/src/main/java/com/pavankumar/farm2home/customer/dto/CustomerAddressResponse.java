package com.pavankumar.farm2home.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressResponse {

    private Long addressId;
    private String addressLine1;
    private String addressLine2;
    private String village;
    private String mandal;
    private String district;
    private String state;
    private String pincode;
    private String landmark;
}