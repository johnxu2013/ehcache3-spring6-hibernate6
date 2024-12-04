package com.john.ehcache3hibernate;

import com.john.ehcache3hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HibernateCacheTest {

    private static SessionFactory sessionFactory;

    @BeforeAll
    public static void setup() {
        Configuration configuration = new Configuration().configure("hibernate6.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    @Test
    public void testSecondLevelCache() {
        Long userId;

        // Step 1: Save a User
/*        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = new User();
            user.setName("John Doe");
            user.setEmail("john.doe@example.com");
            session.persist(user);
            userId = user.getId();
//            userId = (Long) session.save(user);
            session.getTransaction().commit();
        }*/

        // Step 2: Fetch the User (first time - from database)
        User user1;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userId = 1L;
            user1 = session.get(User.class, userId); // Loads from DB
            assertNotNull(user1);
            System.out.println("Fetched from DB: " + user1.getName());
            session.getTransaction().commit();
        }

        // Step 3: Fetch the User (second time - should be from cache)
        User user2;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
//            userId = 2L;
            user2 = session.get(User.class, userId); // Loads from second-level cache
            assertNotNull(user2);
            System.out.println("Fetched from Cache: " + user2.getName());
            session.getTransaction().commit();
        }
    }
}