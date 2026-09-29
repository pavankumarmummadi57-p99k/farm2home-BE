package com.pavankumar.farm2home.customer.service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.customer.dto.CustomerAddressRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface CustomerAddressService {

    ApiResponse getMyAddress(HttpServletRequest request);

    ApiResponse saveAddress(
            CustomerAddressRequest request,
            HttpServletRequest httpServletRequest);

    ApiResponse updateAddress(
            CustomerAddressRequest request,
            HttpServletRequest httpServletRequest);
}