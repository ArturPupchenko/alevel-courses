package com.alevel.courses.jpabox.entity;

import com.alevel.courses.jpabox.entity.abst.AbstractEntityWithGeneratedId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "lessons")
public class Lesson extends AbstractEntityWithGeneratedId {

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "lesson")
    private Collection<Mark> marks = new ArrayList<>();

    @Column(name = "lesson_date_and_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lessonDateAndTime;

    public Lesson() {
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getLessonDateAndTime() {
        return lessonDateAndTime;
    }

    public void setLessonDateAndTime(Date lessonDateAndTime) {
        this.lessonDateAndTime = lessonDateAndTime;
    }

    public Collection<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Collection<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "topic=" + topic.getName() +
                ", group=" + group.getName() +
                ", teacher=" + teacher.getName() +
                ", lessonDateAndTime=" + lessonDateAndTime +
                '}';
    }
}
