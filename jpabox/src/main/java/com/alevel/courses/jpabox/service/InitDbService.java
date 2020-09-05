package com.alevel.courses.jpabox.service;

import com.alevel.courses.jpabox.dao.*;
import com.alevel.courses.jpabox.entity.*;
import com.alevel.courses.jpabox.entity.embeddable.PersonalInformation;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InitDbService {

    private StudentDao studentDao;
    private GroupDao groupDao;
    private TeacherDao teacherDao;
    private CourseDao courseDao;
    private LessonDao lessonDao;
    private TopicDao topicDao;
    private MarkDao markDao;

    public InitDbService(StudentDao studentDao, GroupDao groupDao, TeacherDao teacherDao, CourseDao courseDao, LessonDao lessonDao, TopicDao topicDao, MarkDao markDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
        this.teacherDao = teacherDao;
        this.courseDao = courseDao;
        this.lessonDao = lessonDao;
        this.topicDao = topicDao;
        this.markDao = markDao;
    }

    public void initDb() {
        Student studentOne = new Student();
        studentOne.setName("Ann Ivanova");
        PersonalInformation studentOnePersonalInformation = new PersonalInformation("AnnIvanova@gmail.com", "+39493929945");
        studentOne.setPersonalInformation(studentOnePersonalInformation);

        Group groupOne = new Group();
        groupOne.setName("Software Engineering - 2017");

        studentOne.setGroup(groupOne);

        groupDao.saveOrUpdate(groupOne);

        studentDao.saveOrUpdate(studentOne);

        Teacher teacherOne = new Teacher();
        teacherOne.setName("John White");
        PersonalInformation teacherOnePersonalInformation = new PersonalInformation("JohnWhite@gmail.com", "+35455875665");
        teacherOne.setPersonalInformation(teacherOnePersonalInformation);

        teacherDao.saveOrUpdate(teacherOne);

        Course courseOne = new Course();
        courseOne.setName("C++");

        courseOne.addGroup(groupOne);
        courseOne.addTeacher(teacherOne);

        courseDao.saveOrUpdate(courseOne);

        Topic topicOne = new Topic();
        topicOne.setName("Introduction");
        topicOne.getCourses().add(courseOne);

        topicDao.saveOrUpdate(topicOne);

        Lesson lessonOne = new Lesson();
        lessonOne.setLessonDateAndTime(Date.from(Instant.now()));
        lessonOne.setTopic(topicOne);
        lessonOne.setGroup(groupOne);
        lessonOne.setTeacher(teacherOne);
        lessonDao.saveOrUpdate(lessonOne);

        Mark markOne = new Mark();
        markOne.setLesson(lessonOne);
        markOne.setStudent(studentOne);
        markOne.setValue(90);
        markOne.setTeacher(teacherOne);

        markDao.saveOrUpdate(markOne);


        Student studentTwo = new Student();
        studentTwo.setName("Victor Smirnov");
        PersonalInformation studentTwoPersonalInformation = new PersonalInformation("VictorSmirnov@gmail.com", "+34413453331");
        studentTwo.setPersonalInformation(studentTwoPersonalInformation);

        Group groupTwo = new Group();
        groupTwo.setName("Hardware Engineering - 2018");
        studentTwo.setGroup(groupTwo);

        groupDao.saveOrUpdate(groupTwo);

        studentDao.saveOrUpdate(studentTwo);

        Teacher teacherTwo = new Teacher();
        teacherTwo.setName("Bill Red");
        PersonalInformation teacherTwoPersonalInformation = new PersonalInformation("BillRed@gmail.com", "+35455875555");
        teacherTwo.setPersonalInformation(teacherTwoPersonalInformation);

        teacherDao.saveOrUpdate(teacherTwo);

        Course courseTwo = new Course();
        courseTwo.setName("Java");
        courseTwo.addGroup(groupTwo);
        courseTwo.addTeacher(teacherTwo);

        courseDao.saveOrUpdate(courseTwo);

        Topic topicTwo = new Topic();
        topicTwo.setName("Basics");
        topicTwo.getCourses().add(courseTwo);

        topicDao.saveOrUpdate(topicTwo);

        Lesson lessonTwo = new Lesson();
        lessonTwo.setLessonDateAndTime(Date.from(Instant.now()));
        lessonTwo.setTopic(topicTwo);
        lessonTwo.setGroup(groupTwo);
        lessonTwo.setTeacher(teacherTwo);
        lessonDao.saveOrUpdate(lessonTwo);

        Mark markTwo = new Mark();
        markTwo.setLesson(lessonTwo);
        markTwo.setStudent(studentTwo);
        markTwo.setValue(95);
        markTwo.setTeacher(teacherTwo);

        markDao.saveOrUpdate(markTwo);


        Student studentThree = new Student();
        studentThree.setName("Igor Sharp");
        PersonalInformation studentThreePersonalInformation = new PersonalInformation("IgorSharp@gmail.com", "+34414443331");
        studentThree.setPersonalInformation(studentTwoPersonalInformation);
        studentThree.setGroup(groupOne);

        studentDao.saveOrUpdate(studentThree);


        Student studentFour = new Student();
        studentFour.setName("Ivan Petrov");
        PersonalInformation studentFourPersonalInformation = new PersonalInformation("IvanPetrov@gmail.com", "+34487443331");
        studentFour.setPersonalInformation(studentFourPersonalInformation);
        studentFour.setGroup(groupTwo);

        studentDao.saveOrUpdate(studentFour);


        Topic topicThree = new Topic();
        topicThree.setName("Exam");
        topicThree.getCourses().add(courseOne);
        topicThree.getCourses().add(courseTwo);
        topicDao.saveOrUpdate(topicThree);

        LocalDateTime futureLessonOneTime = LocalDateTime.parse("25-08-2022 12:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Lesson lessonThree = new Lesson();
        lessonThree.setLessonDateAndTime(Timestamp.valueOf(futureLessonOneTime));
        lessonThree.setTopic(topicThree);
        lessonThree.setGroup(groupOne);
        lessonThree.setTeacher(teacherOne);
        lessonDao.saveOrUpdate(lessonThree);

        LocalDateTime futureLessonTwoTime = LocalDateTime.parse("25-04-2022 12:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Lesson lessonFour = new Lesson();
        lessonFour.setLessonDateAndTime(Timestamp.valueOf(futureLessonTwoTime));
        lessonFour.setTopic(topicThree);
        lessonFour.setGroup(groupTwo);
        lessonFour.setTeacher(teacherTwo);
        lessonDao.saveOrUpdate(lessonFour);


        Mark mark2 = new Mark();
        mark2.setLesson(lessonOne);
        mark2.setStudent(studentThree);
        mark2.setValue(65);
        mark2.setTeacher(teacherOne);

        markDao.saveOrUpdate(mark2);
    }


}
