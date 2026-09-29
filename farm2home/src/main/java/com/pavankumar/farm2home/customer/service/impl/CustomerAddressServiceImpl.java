package com.pavankumar.farm2home.customer.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.customer.dto.CustomerAddressRequest;
import com.pavankumar.farm2home.customer.dto.CustomerAddressResponse;
import com.pavankumar.farm2home.customer.entity.CustomerAddress;
import com.pavankumar.farm2home.customer.repository.CustomerAddressRepository;
import com.pavankumar.farm2home.customer.service.CustomerAddressService;
import com.pavankumar.farm2home.security.jwt.JwtService;
import com.pavankumar.farm2home.user.entity.User;
import com.pavankumar.farm2home.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CustomerAddressServiceImpl
        implements CustomerAddressService {

    private final CustomerAddressRepository addressRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public CustomerAddressServiceImpl(
            CustomerAddressRepository addressRepository,
            UserRepository userRepository,
            JwtService jwtService) {

        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    private User getLoggedInUser(HttpServletRequest request) {

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        String token = authHeader.substring(7);

        String phoneNumber =
                jwtService.extractUsername(token);

        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null);
    }

    private CustomerAddressResponse map(
            CustomerAddress address) {

        CustomerAddressResponse response =
                new CustomerAddressResponse();

        response.setAddressId(address.getId());
        response.setAddressLine1(address.getAddressLine1());
        response.setAddressLine2(address.getAddressLine2());
        response.setVillage(address.getVillage());
        response.setMandal(address.getMandal());
        response.setDistrict(address.getDistrict());
        response.setState(address.getState());
        response.setPincode(address.getPincode());
        response.setLandmark(address.getLandmark());

        return response;
    }

    @Override
    public ApiResponse getMyAddress(
            HttpServletRequest request) {

        User user = getLoggedInUser(request);

        if (user == null) {
            return new ApiResponse(
                    false,
                    "User not found.",
                    null);
        }

        Optional<CustomerAddress> optional =
                addressRepository.findByUser(user);

        if (optional.isEmpty()) {
            return new ApiResponse(
                    true,
                    "Delivery address not added yet.",
                    null);
        }

        return new ApiResponse(
                true,
                "Delivery address fetched successfully.",
                map(optional.get()));
    }

    @Override
    @Transactional
    public ApiResponse saveAddress(
            CustomerAddressRequest request,
            HttpServletRequest httpServletRequest) {

        User user =
                getLoggedInUser(httpServletRequest);

        if (user == null) {
            return new ApiResponse(
                    false,
                    "User not found.",
                    null);
        }

        if (addressRepository.findByUser(user).isPresent()) {
            return new ApiResponse(
                    false,
                    "Delivery address already exists. Please update it.",
                    null);
        }

        CustomerAddress address =
                new CustomerAddress();

        address.setUser(user);

        fillAddress(address, request);

        CustomerAddress saved =
                addressRepository.save(address);

        return new ApiResponse(
                true,
                "Delivery address saved successfully.",
                map(saved));
    }

    @Override
    @Transactional
    public ApiResponse updateAddress(
            CustomerAddressRequest request,
            HttpServletRequest httpServletRequest) {

        User user =
                getLoggedInUser(httpServletRequest);

        if (user == null) {
            return new ApiResponse(
                    false,
                    "User not found.",
                    null);
        }

        CustomerAddress address =
                addressRepository
                        .findByUser(user)
                        .orElse(null);

        if (address == null) {
            return new ApiResponse(
                    false,
                    "Delivery address not found.",
                    null);
        }

        fillAddress(address, request);

        CustomerAddress saved =
                addressRepository.save(address);

        return new ApiResponse(
                true,
                "Delivery address updated successfully.",
                map(saved));
    }

    private void fillAddress(
            CustomerAddress address,
            CustomerAddressRequest request) {

        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setVillage(request.getVillage());
        address.setMandal(request.getMandal());
        address.setDistrict(request.getDistrict());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setLandmark(request.getLandmark());
    }
}