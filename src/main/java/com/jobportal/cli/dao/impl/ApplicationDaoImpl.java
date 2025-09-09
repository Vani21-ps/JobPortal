package com.jobportal.cli.dao.impl;

import com.jobportal.cli.dao.ApplicationDao;
import com.jobportal.cli.model.Application;
import com.jobportal.cli.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ApplicationDaoImpl implements ApplicationDao {
    @Override
    public Application apply(Application a) {
        EntityManager em = JpaUtil.em();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
        return a;
    }

    @Override
    public List<Application> findByCandidate(int candidateId) {
        EntityManager em = JpaUtil.em();
        TypedQuery<Application> q = em.createQuery("SELECT a FROM Application a WHERE a.candidateId = :c ORDER BY a.appliedAt DESC", Application.class);
        q.setParameter("c", candidateId);
        List<Application> list = q.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Application> findByJob(int jobId) {
        EntityManager em = JpaUtil.em();
        TypedQuery<Application> q = em.createQuery("SELECT a FROM Application a WHERE a.jobId = :j ORDER BY a.appliedAt DESC", Application.class);
        q.setParameter("j", jobId);
        List<Application> list = q.getResultList();
        em.close();
        return list;
    }

    @Override
    public Optional<Application> findById(int id) {
        EntityManager em = JpaUtil.em();
        Application a = em.find(Application.class, id);
        em.close();
        return a == null ? Optional.empty() : Optional.of(a);
    }
}
