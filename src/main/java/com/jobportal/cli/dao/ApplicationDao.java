package com.jobportal.cli.dao;

import com.jobportal.cli.model.Application;
import java.util.List;
import java.util.Optional;

public interface ApplicationDao {
    Application create(Application app);
    Optional<Application> findById(int id);
    List<Application> byCandidate(int candidateId);
    List<Application> byJob(int jobId);

    // New methods for features
    List<Application> findByCandidateId(int candidateId);
    List<Application> findByJobId(int jobId);
    void updateStatus(int applicationId, String status);
    int countApplications();

    // ✅ New method for duplicate application check
    Optional<Application> findByJobIdAndCandidateId(int jobId, int candidateId);
}
