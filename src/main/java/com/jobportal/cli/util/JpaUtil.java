package com.jobportal.cli.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jobportal-unit");
    public static EntityManager em() {
        return emf.createEntityManager();
    }
}
