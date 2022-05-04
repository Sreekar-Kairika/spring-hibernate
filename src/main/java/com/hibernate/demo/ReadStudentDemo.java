package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            System.out.println("Saving the Students");
            // create the object
            Student student1 = new Student("Daffy", "Duck", "daffy@zemoso.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            session.save(student1);

            // commit transaction
            session.getTransaction().commit();

            //find out what is student's id: primary key
            System.out.println("Saved Student.Generated id:"+student1.getId());

            // now get a new session start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            //retrieve student based on primary key
            System.out.println("Getting student with id: "+student1.getId());

            Student myStudent = session.get(Student.class, student1.getId());

            System.out.println("Get complete: "+myStudent);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

}
