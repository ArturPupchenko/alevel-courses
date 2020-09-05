package com.alevel.courses.jpabox.service;

import com.alevel.courses.jpabox.dao.*;
import com.alevel.courses.jpabox.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.*;

public class TeacherService {

    private SessionFactory sessionFactory;
    private StudentDao studentDao;
    private GroupDao groupDao;
    private TeacherDao teacherDao;
    private CourseDao courseDao;
    private LessonDao lessonDao;
    private TopicDao topicDao;
    private MarkDao markDao;

    public TeacherService(SessionFactory sessionFactory, StudentDao studentDao, GroupDao groupDao, TeacherDao teacherDao, CourseDao courseDao, LessonDao lessonDao, TopicDao topicDao, MarkDao markDao) {
        this.sessionFactory = sessionFactory;
        this.studentDao = studentDao;
        this.groupDao = groupDao;
        this.teacherDao = teacherDao;
        this.courseDao = courseDao;
        this.lessonDao = lessonDao;
        this.topicDao = topicDao;
        this.markDao = markDao;
    }

    public Group getMostSuccessfulGroup(Long teacherId) {
        long nowLong = System.currentTimeMillis();
        Timestamp nowTimestamp = new Timestamp(nowLong);
        Group mostSuccessfulGroup = null;

        Session session = sessionFactory.openSession();
        Teacher teacher = teacherDao.findById(teacherId);

//        Query hql1 = session.createQuery("FROM Course");
//        List<Course> allCourses = new ArrayList<>();
//        allCourses = (List<Course>) hql1.list();
//        List<Course> currentTeacherCourses = new ArrayList<>();
//        Query hql1 = session.createQuery("FROM Teacher T WHERE T.id = :t");
//        hql1.setParameter("t", teacherId);

        List<Course> currentTeacherCourses = new ArrayList<>();
        currentTeacherCourses = ( List<Course>) teacher.getCourses();
//        for (Course course : allCourses) {
//            if (course.getTeachers().contains(teacher)) currentTeacherCourses.add(course);
//        }

        Course oneOfTeacherCourses = currentTeacherCourses.get(0);
        List<Group> teacherGroups = (List<Group>) oneOfTeacherCourses.getGroups();

        Map<Long, Lesson> groupLessons = new HashMap<>();
        for (Group group : teacherGroups) {
            Query hql2 = session.createQuery("FROM Lesson L WHERE L.group.id = :g AND L.teacher.id = :t AND L.lessonDateAndTime < :now ORDER BY L.lessonDateAndTime DESC");
            Long groupId = group.getId();
            hql2.setParameter("t", teacherId);
            hql2.setParameter("g", groupId);
            hql2.setParameter("now", nowTimestamp);
            Lesson lastLessonOfGroupWithThisTeacher = (Lesson) hql2.list().get(0);
            groupLessons.put(groupId, lastLessonOfGroupWithThisTeacher);
        }

        Map<Long, Integer> groupMediums = new HashMap<>();
        for (Map.Entry<Long, Lesson> entry : groupLessons.entrySet()) {
            Integer groupMedium = findMediumMark((List<Mark>) entry.getValue().getMarks());
            groupMediums.put(entry.getKey(), groupMedium);
        }
        Map.Entry<Long, Integer> maxMedium = Collections.max(groupMediums.entrySet(), Comparator.comparing(Map.Entry::getValue));
        mostSuccessfulGroup = groupDao.findById(maxMedium.getKey());
        return mostSuccessfulGroup;
    }

    public static Integer findMediumMark(List<Mark> marks) {
        List<Integer> values = new ArrayList<>();
        for (Mark mark : marks) {
            values.add(mark.getValue());
        }
        Collections.sort(values);
        Integer medium = values.get((values.size() / 2) - 1);
        return medium;

    }

}
