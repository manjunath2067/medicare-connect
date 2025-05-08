package com.example.medicare.connect.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔥 Add this line
    private Long id;

    private String specialization;
    private int yearsOfExperience;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;  // Reference to the common 'Users' entity

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Appointment> appointments; // One-to-many relationship with appointments
}
// Note: The 'Users' entity should have a one-to-one relationship with the 'Doctor' entity.
