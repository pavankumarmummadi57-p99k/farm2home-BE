package com.pavankumar.farm2home.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.user.dto.LoginRequest;
import com.pavankumar.farm2home.user.dto.RegisterRequest;
import com.pavankumar.farm2home.user.dto.SendOtpRequest;
import com.pavankumar.farm2home.user.dto.VerifyOtpRequest;
import com.pavankumar.farm2home.user.service.OtpService;
import com.pavankumar.farm2home.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class UserController {

    private final UserService userService;
    private final OtpService otpService;

    public UserController(UserService userService,
                          OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {

        ApiResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {

        ApiResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse> sendOtp(@Valid @RequestBody SendOtpRequest request) {

        ApiResponse response = otpService.sendOtp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {

        ApiResponse response = otpService.verifyOtp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<ApiResponse> resendOtp(@Valid @RequestBody SendOtpRequest request) {

        ApiResponse response = otpService.resendOtp(request);
        return ResponseEntity.ok(response);
    }

}