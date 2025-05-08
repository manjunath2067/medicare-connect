package com.example.medicare.connect.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medicare.connect.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
