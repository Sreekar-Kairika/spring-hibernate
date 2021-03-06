package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;
            // start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting student with id "+studentId);

            Student myStudent = session.get(Student.class,studentId);

            System.out.println("Updating student ..");
            myStudent.setFirstName("Scooby");

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");

            // NEW CODE
            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            System.out.println("Update email for all students");

            session.createQuery("update Student set email='foo@gmail.com' ").executeUpdate() ;

            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }

}
