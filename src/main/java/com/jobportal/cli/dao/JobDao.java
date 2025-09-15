package com.jobportal.cli.dao;

import com.jobportal.cli.model.Job;
import java.util.List;
import java.util.Optional;

public interface JobDao {
    Job create(Job job);
    Optional<Job> findById(int id);
    List<Job> findAll();
    List<Job> byEmployer(int employerId);

    // New methods for features
    List<Job> search(String title, String location);
    void updateJob(int jobId, String title, String location, String description);
    void deleteJob(int jobId);
    int countJobs();
}
