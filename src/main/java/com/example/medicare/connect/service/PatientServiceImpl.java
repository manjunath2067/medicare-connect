package com.example.medicare.connect.service;

import com.example.medicare.connect.model.Patient;
import com.example.medicare.connect.model.Users;
import com.example.medicare.connect.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        Users existingUser = existingPatient.getUser();
        Users newUser = patient.getUser();

        if (newUser != null) {
            if (newUser.getName() != null) existingUser.setName(newUser.getName());
            if (newUser.getEmail() != null) existingUser.setEmail(newUser.getEmail());
            if (newUser.getPhone() != null) existingUser.setPhone(newUser.getPhone());
            if (newUser.getGender() != null) existingUser.setGender(newUser.getGender());
        }

        if (patient.getAddress() != null) {
            existingPatient.setAddress(patient.getAddress());
        }

        if (patient.getAge() != 0) {
            existingPatient.setAge(patient.getAge());
        }
        return patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
