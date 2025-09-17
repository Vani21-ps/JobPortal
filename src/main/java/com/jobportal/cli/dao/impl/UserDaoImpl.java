package com.jobportal.cli.dao.impl;

import com.jobportal.cli.dao.UserDao;
import com.jobportal.cli.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    public UserDaoImpl() {
        this.em = null;
    }

    @Override
    public User create(User user) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = em.createQuery(
            "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        List<User> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public void updateProfile(int userId, String name, String email, String resumePath) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User user = em.find(User.class, userId);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setResumePath(resumePath);
            em.merge(user);
        }
        tx.commit();
    }

    @Override
    public void deleteUser(int userId) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User user = em.find(User.class, userId);
        if (user != null) {
            em.remove(user);
        }
        tx.commit();
    }

    @Override
    public int countUsers() {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u", Long.class)
                       .getSingleResult();
        return count.intValue();
    }
}
