package com.pavankumar.farm2home.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pavankumar.farm2home.user.entity.User;
import com.pavankumar.farm2home.user.enums.AccountStatus;
import com.pavankumar.farm2home.user.enums.Role;
import com.pavankumar.farm2home.user.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.existsByPhoneNumber("9999999999")) {
            return;
        }

        User admin = new User();

        admin.setFullName("System Admin");
        admin.setPhoneNumber("9999999999");
        admin.setEmail("admin@farm2home.com");
        admin.setPassword(passwordEncoder.encode("Admin@123"));
        admin.setRole(Role.ADMIN);
        admin.setAccountStatus(AccountStatus.ACTIVE);

        userRepository.save(admin);

        System.out.println("✅ Default Admin Created");
    }
}