package com.jobportal.cli.service;

import com.jobportal.cli.dao.JobDao;
import com.jobportal.cli.model.Job;

import java.util.List;
import java.util.Optional;

public class JobService {
    private final JobDao jobDao;
    public JobService(JobDao jobDao){ this.jobDao = jobDao; }
    public Job postJob(Job j){ return jobDao.create(j); }
    public List<Job> listAll(){ return jobDao.findAll(); }
    public List<Job> byEmployer(int id){ return jobDao.findByEmployer(id); }
    public Optional<Job> byId(int id){ return jobDao.findById(id); }
}
