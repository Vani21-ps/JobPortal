package com.jobportal.cli.dao;

import com.jobportal.cli.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User u);
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    List<User> findAll();
}
