package com.example.hotelbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.hotelbackend.security.jwt.AuthTokenFilter;
import com.example.hotelbackend.security.jwt.JwtAuthEntryPoint;
import com.example.hotelbackend.security.user.HotelUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig {

    private final HotelUserDetailsService userDetailsService;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    @Bean
    public AuthTokenFilter authenticationTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {

        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {}) // Enable CORS
            .csrf(AbstractHttpConfigurer::disable)

            .exceptionHandling(exception ->
                    exception.authenticationEntryPoint(jwtAuthEntryPoint))

            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                    // public APIs
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/rooms/**").permitAll()
                    .requestMatchers("/bookings/**").permitAll()

                    // allow preflight requests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // admin APIs
                    .requestMatchers("/roles/**").hasRole("ADMIN")

                    // other requests
                    .anyRequest().authenticated()
            );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(
                authenticationTokenFilter(),
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}}

    //  @Bean(name = "multipartResolver")
    //  public CommonsMultipartResolver multipartResolver() {
    //      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    //      return multipartResolver;
    //  }





}
