package com.example.medicare.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicare.connect.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // This interface will automatically provide CRUD operations for the Patient entity

}
