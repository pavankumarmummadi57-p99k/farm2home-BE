package com.pavankumar.farm2home.user.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.security.jwt.JwtService;
import com.pavankumar.farm2home.user.dto.LoginRequest;
import com.pavankumar.farm2home.user.dto.RegisterRequest;
import com.pavankumar.farm2home.user.entity.User;
import com.pavankumar.farm2home.user.enums.AccountStatus;
import com.pavankumar.farm2home.user.enums.Role;
import com.pavankumar.farm2home.user.repository.UserRepository;
import com.pavankumar.farm2home.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper,
                           JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @Override
    public ApiResponse register(RegisterRequest request) {

        // Confirm Password Validation
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ApiResponse(false, "Password and Confirm Password do not match.", null);
        }

        // Phone Number Already Exists
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            return new ApiResponse(false, "Phone number is already registered.", null);
        }

        // DTO -> Entity
        User user = modelMapper.map(request, User.class);

        // Encode Password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Default Values
        user.setRole(Role.CUSTOMER);
        user.setAccountStatus(AccountStatus.ACTIVE);

        // Save User
        userRepository.save(user);

        return new ApiResponse(true, "User registered successfully.", null);
    }

    @Override
    public ApiResponse login(LoginRequest request) {

        // Check User Exists
        Optional<User> optionalUser = userRepository.findByPhoneNumber(request.getPhoneNumber());

        if (optionalUser.isEmpty()) {
            return new ApiResponse(false, "User not found.", null);
        }

        User user = optionalUser.get();

        // Check Password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ApiResponse(false, "Invalid password.", null);
        }

        // Check Account Status
        if (user.getAccountStatus() != AccountStatus.ACTIVE) {
            return new ApiResponse(false, "Your account is not active.", null);
        }

        // Generate JWT Token
        String token = jwtService.generateToken(user.getPhoneNumber());

        // Response Data
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("phoneNumber", user.getPhoneNumber());
        data.put("role", user.getRole());

        return new ApiResponse(true, "Login successful.", data);
    }

}