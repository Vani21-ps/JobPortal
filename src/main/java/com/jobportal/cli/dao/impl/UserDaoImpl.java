package com.jobportal.cli.dao.impl;

import com.jobportal.cli.dao.UserDao;
import com.jobportal.cli.model.User;
import com.jobportal.cli.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public User create(User u) {
        EntityManager em = JpaUtil.em();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        return u;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        EntityManager em = JpaUtil.em();
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :e", User.class);
        q.setParameter("e", email);
        List<User> list = q.getResultList();
        em.close();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public Optional<User> findById(int id) {
        EntityManager em = JpaUtil.em();
        User u = em.find(User.class, id);
        em.close();
        return u == null ? Optional.empty() : Optional.of(u);
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JpaUtil.em();
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u ORDER BY u.id", User.class);
        List<User> list = q.getResultList();
        em.close();
        return list;
    }
}
