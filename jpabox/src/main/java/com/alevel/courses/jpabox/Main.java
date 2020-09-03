package com.alevel.courses.jpabox;


import com.alevel.courses.jpabox.dao.GroupInfoDao;
import com.alevel.courses.jpabox.dao.StudentScheduleInfoDao;
import com.alevel.courses.jpabox.dao.entityDao.*;
import com.alevel.courses.jpabox.entity.*;
import com.alevel.courses.jpabox.system.InitDbService;
import com.alevel.courses.jpabox.util.DateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            final CourseEntityDao courseDao = new CourseEntityDao(session);
            final GradeEntityDao gradeDao = new GradeEntityDao(session);
            final GroupEntityDao groupDao = new GroupEntityDao(session);
            final LessonEntityDao lessonDao = new LessonEntityDao(session);
            final StudentEntityDao studentDao = new StudentEntityDao(session);
            final TeacherEntityDao teacherDao = new TeacherEntityDao(session);
            final TopicEntityDao topicDao = new TopicEntityDao(session);
            final InitDbService initDB = new InitDbService(courseDao, gradeDao, groupDao,
                    lessonDao, studentDao, teacherDao, topicDao);

            final StudentScheduleInfoDao studentSchedule = new StudentScheduleInfoDao(sessionFactory,
                    groupDao, lessonDao);
            final GroupInfoDao groupInfoDao = new GroupInfoDao(sessionFactory,
                    groupDao, lessonDao, gradeDao);

            session.getTransaction().begin();
            initDB.init();
            session.getTransaction().commit();

            Long studentId = 1L;
            Lesson lesson = studentSchedule.findNextLessonByStId(studentId);

            log.info("Next lesson: Teacher full name: " + lesson.getGroup().getTeacher().getFullName());
            log.info("Next lesson: Topic full name: " + lesson.getTopic().getName());
            log.info("Next lesson: Time: " + DateUtil.formatTime(lesson.getTime()));
            log.info("Next lesson: Date: " + DateUtil.formatDate(lesson.getDate()));

            Long teacherId = 1L;
            Group newGroup = groupInfoDao.newGetMostSucceedGroupById(teacherId);

            log.info("Most succeed group's name: " + newGroup.getName());
            log.info("Most succeed group's teacher: " + newGroup.getTeacher().getFullName());
            log.info("Most succeed group's course: " + newGroup.getCourse().getName());
            log.info("Most succeed group's number: " + newGroup.getCourse().getId());
        }
    }
}