package com.example.medicare.connect.service;

import com.example.medicare.connect.model.Doctor;
import com.example.medicare.connect.model.Users;
import com.example.medicare.connect.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Save a new doctor
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElse(null);
    }

    // Update a doctor
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Users newUser = doctorDetails.getUser();

            Doctor existingDoctor = optionalDoctor.get();
            Users existingUser = existingDoctor.getUser();

            if (newUser != null) {
                if (newUser.getName() != null) existingUser.setName(newUser.getName());
                if (newUser.getEmail() != null) existingUser.setEmail(newUser.getEmail());
                if (newUser.getPhone() != null) existingUser.setPhone(newUser.getPhone());
                if (newUser.getGender() != null) existingUser.setGender(newUser.getGender());
            }

            if (doctorDetails.getSpecialization() != null) {
                existingDoctor.setSpecialization(doctorDetails.getSpecialization());
            }

            if (doctorDetails.getYearsOfExperience() != 0) {
                existingDoctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());
            }
            return doctorRepository.save(existingDoctor);
        }
        return null;
    }

    // Delete a doctor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
