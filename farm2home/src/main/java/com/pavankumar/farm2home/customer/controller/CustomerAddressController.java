package com.pavankumar.farm2home.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavankumar.farm2home.common.dto.ApiResponse;
import com.pavankumar.farm2home.customer.dto.CustomerAddressRequest;
import com.pavankumar.farm2home.customer.service.CustomerAddressService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer/address")
public class CustomerAddressController {

    private final CustomerAddressService addressService;

    public CustomerAddressController(
            CustomerAddressService addressService) {

        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAddress(
            HttpServletRequest request) {

        return ResponseEntity.ok(
                addressService.getMyAddress(request));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveAddress(
            @Valid @RequestBody CustomerAddressRequest request,
            HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok(
                addressService.saveAddress(
                        request,
                        httpServletRequest));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateAddress(
            @Valid @RequestBody CustomerAddressRequest request,
            HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok(
                addressService.updateAddress(
                        request,
                        httpServletRequest));
    }
}