package com.alevel.courses.jpabox.dao;


import com.alevel.courses.jpabox.entity.Group;
import com.alevel.courses.jpabox.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GroupDao {

    private final SessionFactory sessionFactory;

    public GroupDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Group group) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(group);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }


    public Group findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Group group = null;
        try (session) {
            group = (Group) session.get(Group.class, id);
        }
        return group;
    }
}
