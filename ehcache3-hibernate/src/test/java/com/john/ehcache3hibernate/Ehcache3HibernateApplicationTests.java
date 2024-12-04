package com.john.ehcache3hibernate;

import com.john.ehcache3hibernate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Ehcache3HibernateApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void testUserService() {

        String service = userService.getData("mine");
        assertEquals("this mine", service);
        service = userService.getData("mine");
        System.out.println("test done");
    }

}
