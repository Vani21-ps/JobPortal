package com.jobportal.cli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jobportal.cli.dao.*;
import com.jobportal.cli.dao.impl.*;
import com.jobportal.cli.service.*;
import com.jobportal.cli.service.impl.*;
import com.jobportal.cli.util.JpaUtil;

import jakarta.persistence.EntityManager;

@Configuration
public class AppConfig {

    /**
     * Provides a managed EntityManager.
     * Spring will call em.close() on shutdown.
     */
    @Bean(destroyMethod = "close")
    public EntityManager entityManager() {
        return JpaUtil.em();
    }

    // ---------- DAO Beans ----------
    @Bean 
    public UserDao userDao(EntityManager em) { 
        return new UserDaoImpl(em); 
    }

    @Bean 
    public JobDao jobDao(EntityManager em) { 
        return new JobDaoImpl(em); 
    }

    @Bean 
    public ApplicationDao applicationDao(EntityManager em) { 
        return new ApplicationDaoImpl(em); 
    }

    // ---------- Service Beans ----------
    @Bean 
    public AuthService authService(UserDao userDao) { 
        return new AuthService(userDao); 
    }

    @Bean 
    public UserService userService(UserDao userDao) { 
        return new UserServiceImpl(userDao); 
    }

    @Bean 
    public JobService jobService(JobDao jobDao) { 
        return new JobServiceImpl(jobDao); 
    }

    @Bean 
    public ApplicationService applicationService(ApplicationDao applicationDao) {
        return new ApplicationServiceImpl(applicationDao);
    }

    @Bean
    public AdminService adminService(UserDao userDao, JobDao jobDao, ApplicationDao applicationDao) {
        return new AdminServiceImpl(userDao, jobDao, applicationDao);
    }
}
