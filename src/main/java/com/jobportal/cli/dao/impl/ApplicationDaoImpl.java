package com.jobportal.cli.dao.impl;

import com.jobportal.cli.dao.ApplicationDao;
import com.jobportal.cli.model.Application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ApplicationDaoImpl implements ApplicationDao {

    private final EntityManager em;

    public ApplicationDaoImpl(EntityManager em) {
        this.em = em;
    }

    public ApplicationDaoImpl() {
        this.em = null;
    }

    @Override
    public Application create(Application application) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(application);
        tx.commit();
        return application;
    }

    @Override
    public Optional<Application> findById(int id) {
        Application app = em.find(Application.class, id);
        return Optional.ofNullable(app);
    }

    @Override
    public List<Application> byCandidate(int candidateId) {
        TypedQuery<Application> query = em.createQuery(
            "SELECT a FROM Application a WHERE a.candidateId = :candidateId", Application.class);
        query.setParameter("candidateId", candidateId);
        return query.getResultList();
    }

    @Override
    public List<Application> byJob(int jobId) {
        TypedQuery<Application> query = em.createQuery(
            "SELECT a FROM Application a WHERE a.jobId = :jobId", Application.class);
        query.setParameter("jobId", jobId);
        return query.getResultList();
    }

    @Override
    public List<Application> findByCandidateId(int candidateId) {
        return byCandidate(candidateId);
    }

    @Override
    public List<Application> findByJobId(int jobId) {
        return byJob(jobId);
    }

    @Override
    public void updateStatus(int applicationId, String status) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Application app = em.find(Application.class, applicationId);
        if (app != null) {
            app.setStatus(status);
            em.merge(app);
        }
        tx.commit();
    }

    @Override
    public int countApplications() {
        Long count = em.createQuery("SELECT COUNT(a) FROM Application a", Long.class)
                       .getSingleResult();
        return count.intValue();
    }

    // ✅ New method for duplicate check
    @Override
    public Optional<Application> findByJobIdAndCandidateId(int jobId, int candidateId) {
        TypedQuery<Application> query = em.createQuery(
            "SELECT a FROM Application a WHERE a.jobId = :jobId AND a.candidateId = :candidateId", Application.class);
        query.setParameter("jobId", jobId);
        query.setParameter("candidateId", candidateId);
        List<Application> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
}
