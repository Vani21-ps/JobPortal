package com.jobportal.cli.dao.impl;

import com.jobportal.cli.dao.JobDao;
import com.jobportal.cli.model.Job;
import com.jobportal.cli.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JobDaoImpl implements JobDao {
    @Override
    public Job create(Job j) {
        EntityManager em = JpaUtil.em();
        em.getTransaction().begin();
        em.persist(j);
        em.getTransaction().commit();
        em.close();
        return j;
    }

    @Override
    public List<Job> findAll() {
        EntityManager em = JpaUtil.em();
        TypedQuery<Job> q = em.createQuery("SELECT j FROM Job j ORDER BY j.postedAt DESC", Job.class);
        List<Job> list = q.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Job> findByEmployer(int employerId) {
        EntityManager em = JpaUtil.em();
        TypedQuery<Job> q = em.createQuery("SELECT j FROM Job j WHERE j.employerId = :e ORDER BY j.postedAt DESC", Job.class);
        q.setParameter("e", employerId);
        List<Job> list = q.getResultList();
        em.close();
        return list;
    }

    @Override
    public Optional<Job> findById(int id) {
        EntityManager em = JpaUtil.em();
        Job j = em.find(Job.class, id);
        em.close();
        return j == null ? Optional.empty() : Optional.of(j);
    }
}
