package com.alevel.courses.module3.jpahibernate.dao;

import com.alevel.courses.module3.jpahibernate.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AccountDao {

    private final SessionFactory sessionFactory;
    private Session session;

    public AccountDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void saveOrUpdate(Account account) {
        session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(account);
    }

    public Account findAccountById(Long id) {
        session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    public Account findByName(String name) {
        session = sessionFactory.getCurrentSession();
        return session.bySimpleNaturalId(Account.class).load(name);
    }
}
