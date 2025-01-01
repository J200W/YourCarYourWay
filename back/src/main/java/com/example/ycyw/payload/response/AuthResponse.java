package com.example.ycyw.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class AuthResponse {
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String jwtToken;
    @Getter
    private final List<String> roles;
    @Getter
    @Setter
    private Integer statusCode;
}