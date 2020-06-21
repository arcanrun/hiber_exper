package com.flamexander.hibernate.many_to_many;

import com.flamexander.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManyToManyApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/many_to_many/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {

            session = factory.getCurrentSession();

            session.beginTransaction();
            Reader reader = session.get(Reader.class, 1L);
            System.out.println(reader);
            System.out.println("Books: ");
            for (Book b : reader.getBooks()) {
                System.out.println(b.getTitle());
            }

            List<Reader> readers = session.createQuery("SELECT reader FROM Reader reader ORDER BY size(reader.books) DESC").getResultList();
            System.out.println(readers);

            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
