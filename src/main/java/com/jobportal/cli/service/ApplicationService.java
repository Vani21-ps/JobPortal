package com.jobportal.cli.service;

import com.jobportal.cli.model.Application;
import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Application apply(Application app);
    Optional<Application> byId(int id);
    List<Application> byCandidate(int candidateId);
    List<Application> byJob(int jobId);

    // New methods for features
    List<Application> getApplicationsByCandidate(int candidateId);
    List<Application> getApplicationsByJob(int jobId);
    void updateApplicationStatus(int applicationId, String status);
}