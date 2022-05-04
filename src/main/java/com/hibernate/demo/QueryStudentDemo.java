package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            //Query the students
            List<Student> theStudent = session.createQuery("from Student").list();
            //display the students
            for(Student tempStudent : theStudent){
                System.out.println(tempStudent);
            }

            //query student: lastName="Doe"
            System.out.println("Student Name with Doe");
            theStudent = session.createQuery("from Student s where s.lastName='Doe' ").list();
            for(Student tempStudent : theStudent){
                System.out.println(tempStudent);
            }

            //new Query
            System.out.println("New Query");
            theStudent = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy' ").list();
            for(Student tempStudent : theStudent){
                System.out.println(tempStudent);
            }

            //new Query
            System.out.println("Email check with Like");
            theStudent = session.createQuery("from Student s where s.email LIKE '%zemoso.com'").list();
            for(Student tempStudent : theStudent){
                System.out.println(tempStudent);
            }

            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
