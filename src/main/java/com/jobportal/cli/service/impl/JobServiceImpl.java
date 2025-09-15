package com.jobportal.cli.service.impl;

import com.jobportal.cli.dao.JobDao;
import com.jobportal.cli.model.Job;
import com.jobportal.cli.service.JobService;

import java.util.List;
import java.util.Optional;

public class JobServiceImpl implements JobService {
    private final JobDao jobDao;

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public Job postJob(Job job) {
        return jobDao.create(job);
    }

    @Override
    public Optional<Job> byId(int id) {
        return jobDao.findById(id);
    }

    @Override
    public List<Job> listAll() {
        return jobDao.findAll();
    }

    @Override
    public List<Job> byEmployer(int employerId) {
        return jobDao.byEmployer(employerId);
    }

    @Override
    public List<Job> searchJobs(String title, String location) {
        return jobDao.search(title, location);
    }

    @Override
    public void editJob(int jobId, String title, String location, String description) {
        jobDao.updateJob(jobId, title, location, description);
    }

    @Override
    public void deleteJob(int jobId) {
        jobDao.deleteJob(jobId);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobDao.findAll();
    }
}
