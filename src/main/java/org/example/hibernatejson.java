package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;

public class hibernatejson {

    public static void main(String[] args) {
        // Create a Hibernate session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Addresstable.class)
                .buildSessionFactory();

        // Create a session
        try (Session session = factory.getCurrentSession()) {
            // Begin transaction
            session.beginTransaction();

            // Query the Addresstable to retrieve city, country, and pincode
            List<Addresstable> results = session.createQuery("FROM Addresstable").getResultList();

            // Convert the results to JSON format
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> jsonList = new ArrayList<>();
            for (Addresstable address : results) {
                try {
                    String json = objectMapper.writeValueAsString(address);
                    jsonList.add(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            // Print the JSON array
            for (String j:jsonList)
            {
                System.out.println(j);
            }

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close the session factory
            factory.close();
        }
    }
}


