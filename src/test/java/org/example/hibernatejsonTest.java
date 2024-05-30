package org.example;

import org.example.UserInfo;
import org.example.hibernatejson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import static org.junit.jupiter.api.Assertions.*;
public class hibernatejsonTest {

    private static SessionFactory sessionFactory;

    @BeforeAll
    public static void setup() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();
        System.out.println("SessionFactory created");
    }
    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        System.out.println("SessionFactory closed");
    }
    @Test
    public void testDetails() {
        // Arrange
        hibernatejson hibernateJson = new hibernatejson();

        // Act
        String result = hibernateJson.details("sh@gmail.com");

        // Assert
        assertNotNull(result);
        System.out.println("Test result: " + result);
    }

    @Test
    public void testRegisterUser() {
        {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("test3");
            userInfo.setEmail("test3");
            userInfo.setPassword("test3");

            String result = hibernatejson.registerUser(userInfo);
            assertEquals("success", result);
        }
    }
    @Test
    public void testUserExists() {
        boolean exists = hibernatejson.userExists("sh@gmail.com");
        assertTrue(exists);

    }

}