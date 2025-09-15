package com.jobportal.cli.dao;

import com.jobportal.cli.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    // Create
    User create(User u);

    // Read
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    List<User> findAll();

    // Update
    void updateProfile(int userId, String name, String email, String resumePath);

    // Delete
    void deleteUser(int userId);

    // Utility
    int countUsers();
}
