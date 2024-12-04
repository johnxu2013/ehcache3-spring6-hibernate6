package com.john.ehcache3hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class Ehcache3HibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ehcache3HibernateApplication.class, args);
    }

}
