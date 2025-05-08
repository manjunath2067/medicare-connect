package com.example.medicare.connect.service;

import com.example.medicare.connect.model.Users;
import com.example.medicare.connect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save a new user
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Users getUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Update a user
    public Users updateUser(Long id, Users userDetails) {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setRole(userDetails.getRole());
            existingUser.setPhone(userDetails.getPhone());
            existingUser.setGender(userDetails.getGender());
            existingUser.setSpecialization(userDetails.getSpecialization());
            return userRepository.save(existingUser);
        }
        return null;
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
