package com.jobportal.cli.service;

import com.jobportal.cli.dao.ApplicationDao;
import com.jobportal.cli.model.Application;

import java.util.List;
import java.util.Optional;

public class ApplicationService {
    private final ApplicationDao dao;
    public ApplicationService(ApplicationDao dao){ this.dao = dao; }
    public Application apply(Application a){ return dao.apply(a); }
    public List<Application> byCandidate(int id){ return dao.findByCandidate(id); }
    public List<Application> byJob(int id){ return dao.findByJob(id); }
    public Optional<Application> byId(int id){ return dao.findById(id); }
}
