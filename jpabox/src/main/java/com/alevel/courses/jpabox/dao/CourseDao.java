package com.alevel.courses.jpabox.dao;

import com.alevel.courses.jpabox.entity.Course;
import com.alevel.courses.jpabox.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CourseDao {

    private final SessionFactory sessionFactory;

    public CourseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Course course) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Course findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Course course = null;
        try (session) {
            course = (Course) session.get(Course.class, id);
        }
        return course;
    }
}