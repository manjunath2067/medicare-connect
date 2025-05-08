package com.example.medicare.connect.repository;

import com.example.medicare.connect.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // You can add custom queries here if needed
}
