package com.example.medicare.connect.repository;

import com.example.medicare.connect.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // You can add custom queries here if needed
}
