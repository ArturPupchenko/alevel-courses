package com.alevel.courses.jpabox.dao.entityDao;


import com.alevel.courses.jpabox.dao.entityDao.abstractDao.AbstractEntityDao;
import com.alevel.courses.jpabox.entity.Teacher;
import org.hibernate.Session;

import java.util.List;

public class TeacherEntityDao implements AbstractEntityDao<Teacher> {
    private Session session;

    public TeacherEntityDao(Session session) {
        this.session = session;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = session.createQuery("from Teacher").list();
        return teachers;
    }

    @Override
    public void saveOrUpdate(Teacher teacher) {
        session.saveOrUpdate(teacher);
    }

    public Teacher findById(Long id) {
        return session.get(Teacher.class, id);
    }
}
