package com.example.medicare.connect.model;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String role;
    private String message;

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public AuthResponse(
          String token,
          String role,
          String message
    )
    {
        this.token = token;
        this.role = role;
        this.message = message;
    }
}
