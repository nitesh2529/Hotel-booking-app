package com.example.hotelbackend.security;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    private static final Long MAX_AGE = 3600L;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {

        CorsConfiguration config = new CorsConfiguration();

        // allow credentials
        config.setAllowCredentials(true);

        // allowed frontend URLs
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",        // local development
                "https://your-frontend.vercel.app" // deployed frontend
        ));

        // allowed headers
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
        ));

        // allowed HTTP methods
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()
        ));

        // cache preflight request
        config.setMaxAge(MAX_AGE);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // apply CORS to all endpoints
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean =
                new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(-102);

        return bean;
    }
}
