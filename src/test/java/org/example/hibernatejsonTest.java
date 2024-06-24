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

        String result1 = hibernateJson.details("tom@gmail.com");

        // Assert
        assertTrue(result1.length()!=1);

    }
    @Test
    public void testRegisterUser() {

            UserInfo userInfo = new UserInfo();
            userInfo.setName("test3");
            userInfo.setEmail("test3");
            userInfo.setPassword("test3");

            String result = hibernatejson.registerUser(userInfo);
            assertEquals("success", result);


//            UserInfo userInfo1 = new UserInfo();
//            userInfo1.setName("");
//            userInfo1.setEmail("");
//            userInfo1.setPassword("");
//
//            String result1 = hibernatejson.registerUser(userInfo1);
//            assertEquals("fields can't be empty", result1);

    }
    @Test
    public void testUserExists() {
        boolean exists = hibernatejson.userExists("sh@gmail.com");
        assertTrue(exists);

    }
    @Test
    public void testupdateUser() {

        UserInfo userInfo = new UserInfo();
        userInfo.setName("Bhavya");
        userInfo.setEmail("bh@gmail.com");
        userInfo.setPassword("Bhavya");

        String result = hibernatejson.updateUser(userInfo);
        assertEquals("User Details Updated", result);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName("Funny");
        userInfo1.setEmail("b@gmail.com");
        userInfo1.setPassword("funny");
        String result1 = hibernatejson.updateUser(userInfo1);
        assertEquals("Could not update User Details", result1);

        UserInfo userInfo2 = new UserInfo();
        userInfo1.setName("");
        userInfo1.setEmail("");
        userInfo1.setPassword("");
        String result2 = hibernatejson.updateUser(userInfo1);
        assertEquals("Could not update User Details", result2);
    }
    @Test
    public void testdeleteUser() {

        hibernatejson hibernateJson = new hibernatejson();

        String result = hibernateJson.deleteUser("test3");
        assertEquals("User Deleted",result);

        String result1 = hibernateJson.deleteUser("test3");
        assertEquals("Could not Delete user",result1);

    }

}