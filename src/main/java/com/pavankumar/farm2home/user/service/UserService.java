package com.pavankumar.farm2home.user.service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.user.dto.LoginRequest;
import com.pavankumar.farm2home.user.dto.RegisterRequest;

public interface UserService {

    ApiResponse register(RegisterRequest request);

    ApiResponse login(LoginRequest request);

}