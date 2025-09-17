package com.jobportal.cli.dao.impl;

import com.jobportal.cli.dao.JobDao;
import com.jobportal.cli.model.Job;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JobDaoImpl implements JobDao {

    private final EntityManager em;

    
    public JobDaoImpl(EntityManager em) {
        this.em = em;
    }

   
    public JobDaoImpl() {
        this.em = null;
    }

    @Override
    public Job create(Job job) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(job);
        tx.commit();
        return job;
    }

    @Override
    public Optional<Job> findById(int id) {
        Job job = em.find(Job.class, id);
        return Optional.ofNullable(job);
    }

    @Override
    public List<Job> findAll() {
        TypedQuery<Job> query = em.createQuery("SELECT j FROM Job j", Job.class);
        return query.getResultList();
    }

    @Override
    public List<Job> byEmployer(int employerId) {
        TypedQuery<Job> query = em.createQuery(
            "SELECT j FROM Job j WHERE j.employer.id = :employerId", Job.class);
        query.setParameter("employerId", employerId);
        return query.getResultList();
    }

    @Override
    public void updateJob(int jobId, String title, String location, String description) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Job job = em.find(Job.class, jobId);
        if (job != null) {
            job.setTitle(title);
            job.setLocation(location);
            job.setDescription(description);
            em.merge(job);
        }
        tx.commit();
    }

    @Override
    public void deleteJob(int jobId) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Job job = em.find(Job.class, jobId);
        if (job != null) {
            em.remove(job);
        }
        tx.commit();
    }

    @Override
    public int countJobs() {
        Long count = em.createQuery("SELECT COUNT(j) FROM Job j", Long.class)
                       .getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Job> search(String keyword, String location) {
        String jpql = "SELECT j FROM Job j WHERE " +
                      "(LOWER(j.title) LIKE LOWER(:keyword) OR LOWER(j.description) LIKE LOWER(:keyword)) " +
                      "AND LOWER(j.location) LIKE LOWER(:location)";
        TypedQuery<Job> query = em.createQuery(jpql, Job.class);
        query.setParameter("keyword", "%" + keyword + "%");
        query.setParameter("location", "%" + location + "%");
        return query.getResultList();
    }
}
