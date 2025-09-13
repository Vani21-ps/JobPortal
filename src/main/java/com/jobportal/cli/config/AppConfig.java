package com.jobportal.cli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jobportal.cli.dao.*;
import com.jobportal.cli.dao.impl.*;
import com.jobportal.cli.service.*;

@Configuration
public class AppConfig {
    @Bean public UserDao userDao() { return new UserDaoImpl(); }
    @Bean public JobDao jobDao() { return new JobDaoImpl(); }
    @Bean public ApplicationDao applicationDao() { return new ApplicationDaoImpl(); }
    @Bean public AuthService authService(UserDao userDao) { return new AuthService(userDao); }
    @Bean public UserService userService(UserDao userDao) { return new UserService(userDao); }
    @Bean public JobService jobService(JobDao jobDao) { return new JobService(jobDao); }
    @Bean public ApplicationService applicationService(ApplicationDao applicationDao) {
        return new ApplicationService(applicationDao);
    }
}