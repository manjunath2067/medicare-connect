package com.example.medicare.connect.service;

import java.util.List;
import java.util.Optional;

import com.example.medicare.connect.model.Patient;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
}
