package com.alevel.courses.modules.module3.dao;

import com.alevel.courses.modules.module3.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AccountDao {

    private final SessionFactory sessionFactory;

    public AccountDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Account account) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Account getById(Long accountId) {
        Transaction transaction = null;
        Account account = null;
        try (Session session = sessionFactory.openSession()) {
            account = session.get(Account.class, accountId);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
        return account;
    }
}

