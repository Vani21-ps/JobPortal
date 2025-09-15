package com.jobportal.cli.service;

import com.jobportal.cli.model.Job;
import java.util.List;
import java.util.Optional;

public interface JobService {
    Job postJob(Job job);
    Optional<Job> byId(int id);
    List<Job> listAll();
    List<Job> byEmployer(int employerId);

    // New methods for features
    List<Job> searchJobs(String title, String location);
    void editJob(int jobId, String title, String location, String description);
    void deleteJob(int jobId);
    List<Job> getAllJobs();
}