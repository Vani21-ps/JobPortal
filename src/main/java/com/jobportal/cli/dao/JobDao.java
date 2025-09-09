package com.jobportal.cli.dao;

import com.jobportal.cli.model.Job;
import java.util.List;
import java.util.Optional;

public interface JobDao {
    Job create(Job j);
    List<Job> findAll();
    List<Job> findByEmployer(int employerId);
    Optional<Job> findById(int id);
}
