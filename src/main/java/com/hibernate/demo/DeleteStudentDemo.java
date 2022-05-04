package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 5;
            // start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting student with id :"+studentId);

            Student myStudent = session.get(Student.class,studentId);

            //delete
            System.out.println("Deleting student :"+myStudent);
            session.delete(myStudent);

            //delete with id=3
            System.out.println("Deleting student with id-3 ");
            session.createQuery("delete from Student where id=4").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");



        }
        finally {
            factory.close();
        }
    }

}
