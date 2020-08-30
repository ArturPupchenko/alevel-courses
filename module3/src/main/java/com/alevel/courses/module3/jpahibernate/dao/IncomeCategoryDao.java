package com.alevel.courses.module3.jpahibernate.dao;

import com.alevel.courses.module3.jpahibernate.entity.IncomeCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class IncomeCategoryDao {
    private final SessionFactory sessionFactory;
    private Session session;

    public IncomeCategoryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(IncomeCategory category) {
        session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    public IncomeCategory findByName(String name) {
        session = sessionFactory.getCurrentSession();
        return session.bySimpleNaturalId(IncomeCategory.class).load(name);
    }
}
