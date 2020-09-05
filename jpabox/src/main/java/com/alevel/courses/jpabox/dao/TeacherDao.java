package com.alevel.courses.jpabox.dao;


import com.alevel.courses.jpabox.entity.Student;
import com.alevel.courses.jpabox.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TeacherDao {

    private final SessionFactory sessionFactory;

    public TeacherDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(teacher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Teacher findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Teacher teacher = null;
        try (session) {
            teacher = (Teacher) session.get(Teacher.class, id);
        }
        return teacher;
    }
}