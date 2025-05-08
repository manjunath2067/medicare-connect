package com.example.medicare.connect.model;

import com.example.medicare.connect.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    private String phone;

    private String gender;

    private String specialization; // if you want to allow it in base User entity

}
