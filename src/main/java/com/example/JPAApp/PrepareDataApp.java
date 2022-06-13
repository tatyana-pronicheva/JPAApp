package com.example.JPAApp;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PrepareDataApp {
    public static void prepareData() {
        SessionFactory factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            Path path = Paths.get("full.sql");
            Stream<String> lines = Files.lines(path);
            session.beginTransaction();
            lines.forEach(s -> session.createNativeQuery(s).executeUpdate());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        prepareData();
    }
}
