package com.alevel.courses.jpabox.dao.entityDao;


import com.alevel.courses.jpabox.dao.entityDao.abstractDao.AbstractEntityDao;
import com.alevel.courses.jpabox.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class StudentEntityDao implements AbstractEntityDao<Student> {
    private Session session;

    public StudentEntityDao(Session session) {
        this.session = session;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = session.createQuery("from Student").list();
        return students;
    }

    @Override
    public void saveOrUpdate(Student student) {
        session.saveOrUpdate(student);
    }

    public Student findById(Long id) {
        return session.get(Student.class, id);
    }
}
