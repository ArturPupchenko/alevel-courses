package com.alevel.courses.jpabox.dao;


import com.alevel.courses.jpabox.entity.Lesson;
import com.alevel.courses.jpabox.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LessonDao {

    private final SessionFactory sessionFactory;

    public LessonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Lesson lesson) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(lesson);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Lesson findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Lesson lesson = null;
        try (session) {
            lesson = (Lesson) session.get(Lesson.class, id);
        }
        return lesson;
    }
    }
