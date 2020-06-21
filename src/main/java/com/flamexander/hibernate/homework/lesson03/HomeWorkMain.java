package com.flamexander.hibernate.homework.lesson03;

import com.flamexander.hibernate.PrepareDataApp;
import com.flamexander.hibernate.homework.lesson03.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HomeWorkMain {

    private static Session session;
    private static SessionFactory factory;

    public static void main(String[] args) {
//        PrepareDataApp.forcePrepareData();


        factory = new Configuration()
                .configure("configs/homework.lesson03/hibernate.cfg.xml")
                .buildSessionFactory();


        try {

            buy(6,1);
            buy(6,3);
            buy(6,4);


           showClientsByGood(1);




        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static void createUser(String name) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Buyer newBuyer = new Buyer(name);
        session.save(newBuyer);
        session.getTransaction().commit();
    }

    public static void createGood(String title, int price) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Good newGood = new Good(title, price);
        session.save(newGood);
        session.getTransaction().commit();
    }

    public static void deleteGood(int id) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Good deleteGood = session.get(Good.class, id);
        session.delete(deleteGood);
        session.getTransaction().commit();
    }

    public static void deleteUser(int id) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(Buyer.class, id));
        session.getTransaction().commit();
    }

    public static void buy(int userId, int goodId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Buyer buyer = session.get(Buyer.class, userId);
        Good good = session.get(Good.class, goodId);
        buyer.buy(good);
        session.getTransaction().commit();
    }

    public static void showUserPurchases(int userId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Buyer buyer = session.get(Buyer.class, userId);
        System.out.println(buyer + ": ");
        for (Good g : buyer.getGoods()) {
            System.out.println("\t\t" + g.getTitle() + "__" + g.getPrice());
        }
        session.getTransaction().commit();
    }


    public static void showClientsByGood(int goodId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Good good = session.get(Good.class, goodId);
        System.out.println(good + ": ");
        for (Buyer b : good.getBuyers()) {
            System.out.println("\t\t" + b.getName());
        }
        session.getTransaction().commit();
    }


}
