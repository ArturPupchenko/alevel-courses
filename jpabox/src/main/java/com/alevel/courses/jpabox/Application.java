package com.alevel.courses.jpabox;


import com.alevel.courses.jpabox.dao.*;
import com.alevel.courses.jpabox.entity.Group;
import com.alevel.courses.jpabox.entity.Lesson;
import com.alevel.courses.jpabox.service.InitDbService;
import com.alevel.courses.jpabox.service.StudentShudulerService;
import com.alevel.courses.jpabox.service.TeacherService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {

            GroupDao groupDao = new GroupDao(sessionFactory);
            StudentDao studentDao = new StudentDao(sessionFactory);
            TeacherDao teacherDao = new TeacherDao(sessionFactory);
            CourseDao courseDao = new CourseDao(sessionFactory);
            LessonDao lessonDao = new LessonDao(sessionFactory);
            TopicDao topicDao = new TopicDao(sessionFactory);
            MarkDao markDao = new MarkDao(sessionFactory);

            InitDbService initDbService = new InitDbService(studentDao, groupDao, teacherDao, courseDao, lessonDao, topicDao, markDao);

            initDbService.initDb();

            StudentShudulerService studentShudulerService = new StudentShudulerService(sessionFactory, studentDao, groupDao, teacherDao, courseDao, lessonDao, topicDao, markDao);

            Long studentId = 1L;
            Lesson closestLesson = studentShudulerService.getClosestLessonOfStudent(studentId);
            if (closestLesson != null) {
                String studentName = studentDao.findById(studentId).getName();
                log.info("Find closest lesson for student with id = " + studentId);
                log.info(studentName + "`s closest lesson is " + closestLesson.toString());
            }

            TeacherService teacherService = new TeacherService(sessionFactory, studentDao, groupDao, teacherDao, courseDao, lessonDao, topicDao, markDao);
            Long teacherId = 1L;
            Group mostSuccessfulGroup = teacherService.getMostSuccessfulGroup(teacherId);
            if (closestLesson != null) {
                String teacherName = teacherDao.findById(teacherId).getName();
                log.info("Find most successful group of teacher with id = " + teacherId);
                log.info(teacherName + "`s best group is " + mostSuccessfulGroup.getName().toString());
            }

        }
    }

}
