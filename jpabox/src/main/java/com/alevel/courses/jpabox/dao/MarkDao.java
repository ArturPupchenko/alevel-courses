package com.alevel.courses.jpabox.dao;


import com.alevel.courses.jpabox.entity.Mark;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MarkDao {

    private final SessionFactory sessionFactory;

    public MarkDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Mark mark) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(mark);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Mark findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Mark mark = null;
        try (session) {
            mark = (Mark) session.get(Mark.class, id);
        }
        return mark;
    }
}
