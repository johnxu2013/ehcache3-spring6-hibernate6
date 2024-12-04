package com.john.ehcache3hibernate.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable("server-test")
    public String getData(String plan) {
        System.out.println("in getData");
        return "this " + plan;
    }
}
