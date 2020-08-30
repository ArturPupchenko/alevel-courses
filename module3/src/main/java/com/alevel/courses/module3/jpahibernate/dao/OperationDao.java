package com.alevel.courses.module3.jpahibernate.dao;


import com.alevel.courses.module3.jpahibernate.entity.Operation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OperationDao {
    private final SessionFactory sessionFactory;
    private Session session;

    public OperationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Operation operation) {
        session = sessionFactory.getCurrentSession();
        session.save(operation);
    }
}
