package com.example.medicare.connect.service;

import com.example.medicare.connect.model.Appointment;
import com.example.medicare.connect.model.Doctor;
import com.example.medicare.connect.repository.AppointmentRepository;
import com.example.medicare.connect.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    // Save a new appointment
    public Appointment saveAppointment(Appointment appointment) {
        Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
              .orElseThrow(() -> new RuntimeException("Doctor not found"));
        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElse(null);
    }

    // Update an appointment
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();
            existingAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            existingAppointment.setStatus(appointmentDetails.getStatus());
            existingAppointment.setDoctor(appointmentDetails.getDoctor());
            existingAppointment.setPatient(appointmentDetails.getPatient());
            return appointmentRepository.save(existingAppointment);
        }
        return null;
    }

    // Delete an appointment
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
