package com.example.medicare.connect.model;

import com.example.medicare.connect.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String specialization; // for doctor only
    private Role role; // PATIENT or DOCTOR
    private int age;  // for patient only
    private String address; // for patient only
}

