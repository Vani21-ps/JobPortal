package com.jobportal.cli.service.impl;

import com.jobportal.cli.dao.ApplicationDao;
import com.jobportal.cli.model.Application;
import com.jobportal.cli.service.ApplicationService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDao applicationDao;

    public ApplicationServiceImpl(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Override
    public Application apply(Application app) {
        // Prevent duplicate applications
        Optional<Application> existing = applicationDao.findByJobIdAndCandidateId(app.getJobId(), app.getCandidateId());
        if (existing.isPresent()) {
            throw new RuntimeException("You have already applied for this job.");
        }
        app.setStatus("APPLIED");
        app.setAppliedAt(LocalDateTime.now());
        return applicationDao.create(app);
    }

    @Override
    public Optional<Application> byId(int id) {
        return applicationDao.findById(id);
    }

    @Override
    public List<Application> byCandidate(int candidateId) {
        return applicationDao.byCandidate(candidateId);
    }

    @Override
    public List<Application> byJob(int jobId) {
        return applicationDao.byJob(jobId);
    }

    @Override
    public List<Application> getApplicationsByCandidate(int candidateId) {
        return applicationDao.findByCandidateId(candidateId);
    }

    @Override
    public List<Application> getApplicationsByJob(int jobId) {
        return applicationDao.findByJobId(jobId);
    }

    @Override
    public void updateApplicationStatus(int applicationId, String status) {
        applicationDao.updateStatus(applicationId, status);
    }
}
