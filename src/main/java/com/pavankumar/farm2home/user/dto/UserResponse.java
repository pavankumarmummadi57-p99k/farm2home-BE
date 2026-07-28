package com.pavankumar.farm2home.user.dto;

import com.pavankumar.farm2home.user.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String fullName;

    private String phoneNumber;

    private Role role;

}