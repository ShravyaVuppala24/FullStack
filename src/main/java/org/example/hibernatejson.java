package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class hibernatejson {

    public static String details(String useremail) {


        // Create a Hibernate session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();

        String output = null;
        try (Session session = factory.getCurrentSession()) {
            // Begin transaction
            session.beginTransaction();

            // Create HQL query with a parameter for email
            List<UserInfo> results = session.createQuery("FROM UserInfo u WHERE u.email = :email", UserInfo.class)
                    .setParameter("email", useremail)
                    .getResultList();

            // Convert the results to JSON format
            ObjectMapper objectMapper = new ObjectMapper();

            output = objectMapper.writeValueAsString(results);

            // Commit transaction
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close the session factory
            factory.close();
        }

        return output;
    }

    public static String registerUser(UserInfo userInfo) {

        if (userInfo.getName() == null || userInfo.getName().isEmpty() ||
                userInfo.getEmail() == null || userInfo.getEmail().isEmpty() ||
                userInfo.getPassword() == null || userInfo.getPassword().isEmpty()) {
            return "fields can't be empty";
        }

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            // Begin transaction
            session.beginTransaction();

            // Save the UserInfo object to the database
            session.save(userInfo);

            // Commit transaction
            session.getTransaction().commit();

            // Return success message
            return "success";

        } catch (Exception ex) {
            ex.printStackTrace();
            return "failed";
        } finally {
            // Close the session factory
            factory.close();
        }

    }
    public static boolean userExists(String useremail) {


        // Create a Hibernate session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();

        String output = null;
        try (Session session = factory.getCurrentSession()) {
            // Begin transaction
            session.beginTransaction();

            // Create HQL query with a parameter for email
            List<UserInfo> results = session.createQuery("FROM UserInfo u WHERE u.email = :email", UserInfo.class)
                    .setParameter("email", useremail)
                    .getResultList();

            session.getTransaction().commit();
            return !results.isEmpty();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close the session factory
            factory.close();
        }
    }

    public static String updateUser(UserInfo userInfo) {
        // Create a Hibernate session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession()) {
            // Begin transaction
            session.beginTransaction();

            int updatedEntities = session.createQuery("UPDATE UserInfo u SET u.password = :password WHERE u.email = :email")
                    .setParameter("password", userInfo.getPassword())
                    .setParameter("email", userInfo.getEmail())
                    .executeUpdate();

            session.getTransaction().commit();
            if (updatedEntities > 0) {
                return "User Details Updated";
            }
            else {
                return "Could not update User Details";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Could not update User Details";
        } finally {
            // Close the session factory
            factory.close();
        }

    }

    public static String deleteUser(String email) {
        // Create a Hibernate session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession()) {
            // Begin transaction
            session.beginTransaction();

            int deletedEntities = session.createQuery("DELETE FROM UserInfo u WHERE u.email = :email")
                    .setParameter("email", email)
                    .executeUpdate();

            session.getTransaction().commit();
            if (deletedEntities > 0) {
                return "User Deleted";
            }
            else {
                return "Could not Delete user";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Could not Delete user";
        } finally {
            // Close the session factory
            factory.close();
        }

    }
}

