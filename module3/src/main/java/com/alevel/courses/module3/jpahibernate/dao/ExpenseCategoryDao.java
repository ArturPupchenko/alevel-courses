package com.alevel.courses.module3.jpahibernate.dao;


import com.alevel.courses.module3.jpahibernate.entity.ExpenseCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ExpenseCategoryDao {
    private final SessionFactory sessionFactory;
    private Session session;

    public ExpenseCategoryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(ExpenseCategory category) {
        session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    public ExpenseCategory findByName(String name) {
        session = sessionFactory.getCurrentSession();
        return session.bySimpleNaturalId(ExpenseCategory.class).load(name);
    }
}
