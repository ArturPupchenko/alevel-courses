package com.alevel.courses.jpabox.dao;



import com.alevel.courses.jpabox.entity.Student;
import com.alevel.courses.jpabox.entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TopicDao {

    private final SessionFactory sessionFactory;

    public TopicDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Topic topic) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(topic);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    public Topic findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Topic topic = null;
        try (session) {
            topic = (Topic) session.get(Topic.class, id);
        }
        return topic;
    }
}
