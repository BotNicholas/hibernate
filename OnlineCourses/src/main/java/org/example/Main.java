package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();

        //Getting all the students from the first Course
        //session.get(Course.class, 1).getStudents().forEach((s)-> System.out.println(s.getName()));

        //obtaining Subscription object
        //System.out.println(session.get(Subscription.class, new SubscriptionId(10, 1)).getSubscriptionDate());





        //!!!!!!!!
        //        String a = "aaa";
        //        String b = "aaa";
        //        System.out.println(a == b); //boxing




        //obtaining PurchaseList object
        //System.out.println(session.get(PurchaseList.class, new PurchaseListId("Фуриков Эрнст", "Data Scientist с 0 до PRO")).getPrice());

        sessionFactory.close();
        session.close();
    }

    public static SessionFactory getSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}