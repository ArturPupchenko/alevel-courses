package com.alevel.courses.modules.module3.dao;

import com.alevel.courses.modules.module3.entity.IncomeCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class IncomeCategoryDao {

    private final SessionFactory sessionFactory;

    public IncomeCategoryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(IncomeCategory incomeCategory) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(incomeCategory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
