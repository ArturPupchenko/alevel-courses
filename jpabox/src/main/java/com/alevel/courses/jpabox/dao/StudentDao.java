package com.alevel.courses.jpabox.dao;

import com.alevel.courses.jpabox.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Student findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Student student= null;
        try (session) {
            student = (Student) session.get(Student.class, id);
        }
        return student;
    }
}
