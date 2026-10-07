////
////
////package com.pavankumar.farm2home.config;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.Customizer;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import com.pavankumar.farm2home.security.jwt.JwtAuthenticationFilter;
////
////@Configuration
////public class SecurityConfig {
////
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////
////    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
////        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf(csrf -> csrf.disable())
////            .cors(Customizer.withDefaults())
////            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////            .authorizeHttpRequests(auth -> auth
////                .requestMatchers("/api/auth/**").permitAll()
////
////                // Must be BEFORE /api/products/** permitAll().
////                .requestMatchers("/api/products/*/farmer-contact").hasRole("CUSTOMER")
////
////                .requestMatchers("/api/products").permitAll()
////                .requestMatchers("/api/products/**").permitAll()
////                .requestMatchers("/swagger-ui/**").permitAll()
////                .requestMatchers("/v3/api-docs/**").permitAll()
////                .requestMatchers("/api/admin/**").hasRole("ADMIN")
////                .anyRequest().authenticated())
////            .httpBasic(Customizer.withDefaults())
////            .formLogin(form -> form.disable());
////
////        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////}
//
//
//package com.pavankumar.farm2home.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.pavankumar.farm2home.security.jwt.JwtAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(
//            JwtAuthenticationFilter jwtAuthenticationFilter) {
//
//        this.jwtAuthenticationFilter =
//                jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(
//            HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(Customizer.withDefaults())
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//
//                        .requestMatchers("/api/auth/**")
//                        .permitAll()
//
//                        /*
//                         * CUSTOMER and FARMER can request another farmer's
//                         * phone number from the Call Farmer button.
//                         *
//                         * Keep this rule BEFORE /api/products/**.
//                         */
//                        .requestMatchers(
//                                "/api/products/*/farmer-contact")
//                        .hasAnyRole("CUSTOMER", "FARMER")
//
//                        .requestMatchers("/api/products")
//                        .permitAll()
//
//                        .requestMatchers("/api/products/**")
//                        .permitAll()
//
//                        .requestMatchers("/swagger-ui/**")
//                        .permitAll()
//
//                        .requestMatchers("/v3/api-docs/**")
//                        .permitAll()
//
//                        .requestMatchers("/api/admin/**")
//                        .hasRole("ADMIN")
//
//                        .anyRequest()
//                        .authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(form -> form.disable());
//
//        http.addFilterBefore(
//                jwtAuthenticationFilter,
//                UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}

package com.pavankumar.farm2home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pavankumar.farm2home.security.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/api/auth/**").permitAll()

                // CUSTOMER and FARMER can use Call Farmer.
                .requestMatchers("/api/products/*/farmer-contact")
                .hasAnyRole("CUSTOMER", "FARMER")

                // Category names can be viewed by the application.
                .requestMatchers(HttpMethod.GET, "/api/categories")
                .permitAll()

                // Category creation belongs to FARMER now.
                .requestMatchers(HttpMethod.POST, "/api/categories")
                .hasRole("FARMER")

                .requestMatchers("/api/products").permitAll()
                .requestMatchers("/api/products/**").permitAll()

                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()

                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .formLogin(form -> form.disable());

        http.addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}
