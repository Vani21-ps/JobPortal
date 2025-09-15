package com.jobportal.cli.service;

import com.jobportal.cli.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> all();
    Optional<User> byId(int id);
    void updateProfile(int userId, String name, String email, String resumePath);
    void deleteUser(int userId);
    int countUsers();

    // New method
    Optional<User> getCandidateProfile(int id);
}

