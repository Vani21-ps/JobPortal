package com.jobportal.cli.service.impl;

import com.jobportal.cli.dao.UserDao;
import com.jobportal.cli.dao.JobDao;
import com.jobportal.cli.dao.ApplicationDao;
import com.jobportal.cli.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserDao userDao;
    private final JobDao jobDao;
    private final ApplicationDao applicationDao;

    public AdminServiceImpl(UserDao userDao, JobDao jobDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.jobDao = jobDao;
        this.applicationDao = applicationDao;
    }

    @Override
    public int getTotalUsers() {
        return userDao.countUsers();
    }

    @Override
    public int getTotalJobs() {
        return jobDao.countJobs();
    }

    @Override
    public int getTotalApplications() {
        return applicationDao.countApplications();
    }
}
