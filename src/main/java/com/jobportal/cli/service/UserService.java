package com.jobportal.cli.service;

import com.jobportal.cli.dao.UserDao;
import com.jobportal.cli.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;
    public UserService(UserDao userDao){ this.userDao = userDao; }
    public List<User> all(){ return userDao.findAll(); }
    public Optional<User> byId(int id){ return userDao.findById(id); }
}
