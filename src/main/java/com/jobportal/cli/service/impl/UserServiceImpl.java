package com.jobportal.cli.service.impl;

import com.jobportal.cli.dao.UserDao;
import com.jobportal.cli.model.User;
import com.jobportal.cli.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> all() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> byId(int id) {
        return userDao.findById(id);
    }

    @Override
    public void updateProfile(int userId, String name, String email, String resumePath) {
        userDao.updateProfile(userId, name, email, resumePath);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public int countUsers() {
        return userDao.countUsers();
    }

    @Override
    public Optional<User> getCandidateProfile(int id) {
        return userDao.findById(id);
    }
}
