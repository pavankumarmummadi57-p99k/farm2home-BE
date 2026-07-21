package com.pavankumar.farm2home.user.service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.user.dto.SendOtpRequest;
import com.pavankumar.farm2home.user.dto.VerifyOtpRequest;

public interface OtpService {

    ApiResponse sendOtp(SendOtpRequest request);

    ApiResponse verifyOtp(VerifyOtpRequest request);

    ApiResponse resendOtp(SendOtpRequest request);

}