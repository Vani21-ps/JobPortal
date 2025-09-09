package com.jobportal.cli.dao;

import com.jobportal.cli.model.Application;
import java.util.List;
import java.util.Optional;

public interface ApplicationDao {
    Application apply(Application a);
    List<Application> findByCandidate(int candidateId);
    List<Application> findByJob(int jobId);
    Optional<Application> findById(int id);
}
