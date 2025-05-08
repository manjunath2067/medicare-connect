package com.example.medicare.connect.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medicare.connect.enums.Role;
import com.example.medicare.connect.model.AuthResponse;
import com.example.medicare.connect.model.Doctor;
import com.example.medicare.connect.model.LoginRequest;
import com.example.medicare.connect.model.Patient;
import com.example.medicare.connect.model.RegisterRequest;
import com.example.medicare.connect.model.Users;
import com.example.medicare.connect.repository.DoctorRepository;
import com.example.medicare.connect.repository.PatientRepository;
import com.example.medicare.connect.repository.UserRepository;
import com.example.medicare.connect.util.JwtUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AuthResponse register(RegisterRequest request) {
        try {
            Role role = Role.valueOf(String.valueOf(request.getRole()));

            Users user = new Users();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setGender(request.getGender());
            user.setSpecialization(request.getSpecialization());
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            user = userRepository.save(user);

            if (role == Role.PATIENT) {
                Patient patient = new Patient();
                patient.setUser(user);
                patient.setAge(request.getAge());
                patient.setAddress(request.getAddress());
                patientRepository.save(patient);
            } else if (role == Role.DOCTOR) {
                Doctor doctor = new Doctor();
                doctor.setUser(user);
                doctor.setSpecialization(request.getSpecialization());
                doctorRepository.save(doctor);
            }

            String token = jwtUtils.generateToken(user);
            return new AuthResponse(token, user.getRole().name(), "Registered successfully");
        } catch (Exception e) {
            throw new RuntimeException("Error during user registration: " + e.getMessage(), e);
        }
    }

    public AuthResponse login(LoginRequest request) {
        Users users = userRepository.findByEmail(request.getEmail())
              .orElseThrow(() -> new UsernameNotFoundException("Invalid Email"));

        if (!passwordEncoder.matches(request.getPassword(), users.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        String token = jwtUtils.generateToken(users);
        return new AuthResponse(token, users.getRole().name(), "Login successful");
    }
}
