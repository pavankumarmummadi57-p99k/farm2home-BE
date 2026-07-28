package com.pavankumar.farm2home.user.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.user.dto.SendOtpRequest;
import com.pavankumar.farm2home.user.dto.VerifyOtpRequest;
import com.pavankumar.farm2home.user.entity.OtpVerification;
import com.pavankumar.farm2home.user.entity.User;
import com.pavankumar.farm2home.user.repository.OtpVerificationRepository;
import com.pavankumar.farm2home.user.repository.UserRepository;
import com.pavankumar.farm2home.user.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpVerificationRepository otpRepository;
    private final UserRepository userRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    public OtpServiceImpl(OtpVerificationRepository otpRepository,
                          UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse sendOtp(SendOtpRequest request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElse(null);

        if (user == null) {
            return new ApiResponse(false, "User not found.", null);
        }

        String otp = generateOtp();

        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setPhoneNumber(request.getPhoneNumber());
        otpVerification.setOtp(otp);
        otpVerification.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpVerification.setVerified(false);
        otpVerification.setAttempts(0);

        otpRepository.save(otpVerification);

        System.out.println("==================================");
        System.out.println("OTP for " + request.getPhoneNumber() + " : " + otp);
        System.out.println("==================================");

        return new ApiResponse(true,
                "OTP generated successfully. (Development Mode - Check Console)",
                null);
    }

    @Override
   
    public ApiResponse verifyOtp(VerifyOtpRequest request) {

        OtpVerification otpVerification = otpRepository
                .findTopByPhoneNumberOrderByCreatedAtDesc(request.getPhoneNumber())
                .orElse(null);

        if (otpVerification == null) {
            return new ApiResponse(false, "OTP not found.", null);
        }

        if (otpVerification.isVerified()) {
            return new ApiResponse(false, "OTP already verified.", null);
        }

        if (LocalDateTime.now().isAfter(otpVerification.getExpiryTime())) {
            return new ApiResponse(false, "OTP has expired.", null);
        }

        if (otpVerification.getAttempts() >= 5) {
            return new ApiResponse(false, "Maximum OTP attempts exceeded.", null);
        }

        if (!otpVerification.getOtp().equals(request.getOtp())) {

            otpVerification.setAttempts(otpVerification.getAttempts() + 1);
            otpRepository.save(otpVerification);

            return new ApiResponse(false, "Invalid OTP.", null);
        }

        otpVerification.setVerified(true);
        otpRepository.save(otpVerification);

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber()).orElse(null);

        if (user != null) {
            user.setAccountStatus(com.pavankumar.farm2home.user.enums.AccountStatus.ACTIVE);
            userRepository.save(user);
        }

        return new ApiResponse(true, "OTP verified successfully.", null);
    }
    
    @Override
    public ApiResponse resendOtp(SendOtpRequest request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElse(null);

        if (user == null) {
            return new ApiResponse(false, "User not found.", null);
        }

        String otp = generateOtp();

        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setPhoneNumber(request.getPhoneNumber());
        otpVerification.setOtp(otp);
        otpVerification.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpVerification.setVerified(false);
        otpVerification.setAttempts(0);

        otpRepository.save(otpVerification);

        System.out.println("==================================");
        System.out.println("Resent OTP for " + request.getPhoneNumber() + " : " + otp);
        System.out.println("==================================");

        return new ApiResponse(
                true,
                "OTP resent successfully. (Development Mode - Check Console)",
                null);
    }
    
    private String generateOtp() {
        return String.valueOf(100000 + secureRandom.nextInt(900000));
    }

}