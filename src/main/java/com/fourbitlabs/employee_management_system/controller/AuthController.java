package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.response.TokenRefreshResponseDto;

import com.fourbitlabs.employee_management_system.dto.request.LoginRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.LoginResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.interfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        
        // Setup HttpOnly Cookie for Refresh Token
        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/api/auth/refresh");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days in seconds
        cookie.setSecure(false); // set to true if using HTTPS usually
        response.addCookie(cookie);

        ApiResponse<?> apiResponse = new ApiResponse<>(200, "Login successful", loginResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response){
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, "Refresh Token is missing in cookies", null));
        }

        TokenRefreshResponseDto responseDto = authService.refreshToken(refreshToken);
        
        // Setup HttpOnly Cookie for the exact new Refresh Token
        Cookie cookie = new Cookie("refreshToken", responseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/api/auth/refresh");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days in seconds
        cookie.setSecure(false);
        response.addCookie(cookie);

        ApiResponse<?> apiResponse = new ApiResponse<>(200, "Token refreshed successfully", responseDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
