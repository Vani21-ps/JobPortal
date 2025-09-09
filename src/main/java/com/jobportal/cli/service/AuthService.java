package com.jobportal.cli.service;

import com.jobportal.cli.dao.UserDao;
import com.jobportal.cli.model.Role;
import com.jobportal.cli.model.User;
import com.jobportal.cli.util.PasswordUtil;

import java.util.Optional;

public class AuthService {
    private final UserDao userDao;
    public AuthService(UserDao userDao){ this.userDao = userDao; }

    public User register(String name, String email, String rawPassword, Role role) {
        Optional<User> ex = userDao.findByEmail(email);
        if (ex.isPresent()) throw new RuntimeException("Email already registered.");
        User u = new User(name, email, PasswordUtil.hash(rawPassword), role);
        return userDao.create(u);
    }

    public User login(String email, String rawPassword) {
        Optional<User> opt = userDao.findByEmail(email);
        if (opt.isEmpty()) throw new RuntimeException("Invalid credentials.");
        User u = opt.get();
        if (!u.getPasswordHash().equals(PasswordUtil.hash(rawPassword))) throw new RuntimeException("Invalid credentials.");
        return u;
    }
}
