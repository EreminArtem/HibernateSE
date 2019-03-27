package ru.eremin.hibernate.se;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @autor Eremin Artem on 27.03.2019.
 */

public class EntityManagerHandler {

    private static final EntityManager em;
    
    static {
        final EntityManagerFactory factory = factory();
        em = factory.createEntityManager();
    }

    private static EntityManagerFactory factory() {
        return Persistence.createEntityManagerFactory("PU");
    }

    public static EntityManager getEntityManager(){
        return em;
    }

}
