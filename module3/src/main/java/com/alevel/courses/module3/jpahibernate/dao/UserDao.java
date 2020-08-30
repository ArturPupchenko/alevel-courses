package com.alevel.courses.module3.jpahibernate.dao;


import com.alevel.courses.module3.jpahibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDao {
    private final SessionFactory sessionFactory;
    private Session session;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void saveOrUpdate(User account) {
        session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(account);
    }

    public User findAccountById(Long id) {
        session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    public User findByName(String name) {
        session = sessionFactory.getCurrentSession();
        return session.bySimpleNaturalId(User.class).load(name);
    }
}
