package com.alevel.courses.jpabox.service;

import com.alevel.courses.jpabox.dao.*;
import com.alevel.courses.jpabox.entity.Group;
import com.alevel.courses.jpabox.entity.Lesson;
import com.alevel.courses.jpabox.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;

public class StudentShudulerService {

    private SessionFactory sessionFactory;
    private StudentDao studentDao;
    private GroupDao groupDao;
    private TeacherDao teacherDao;
    private CourseDao courseDao;
    private LessonDao lessonDao;
    private TopicDao topicDao;
    private MarkDao markDao;

    public StudentShudulerService(SessionFactory sessionFactory, StudentDao studentDao, GroupDao groupDao, TeacherDao teacherDao, CourseDao courseDao, LessonDao lessonDao, TopicDao topicDao, MarkDao markDao) {
        this.sessionFactory = sessionFactory;
        this.studentDao = studentDao;
        this.groupDao = groupDao;
        this.teacherDao = teacherDao;
        this.courseDao = courseDao;
        this.lessonDao = lessonDao;
        this.topicDao = topicDao;
        this.markDao = markDao;
    }

    public Lesson getClosestLessonOfStudent(Long studentId) {
        long nowLong = System.currentTimeMillis();
        Timestamp nowTimestamp = new Timestamp(nowLong);

        Lesson closestLesson = null;

        Session session = sessionFactory.openSession();

        Student student = studentDao.findById(studentId);
        Group group = student.getGroup();
        Long groupId = group.getId();

        try (session) {
            Query q = session.createQuery("FROM Lesson L where L.group.id = :g and L.lessonDateAndTime > :now");
            q.setParameter("g", groupId);
            q.setParameter("now", nowTimestamp);
            closestLesson = (Lesson) q.list().get(0);
        }
        return closestLesson;
    }


}
