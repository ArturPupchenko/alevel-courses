package com.alevel.courses.jpabox.entity;


import javax.persistence.*;

@Entity
@Table(name = "topics")
public class Topic extends AbstractEntityWithGeneratedId {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private String name;

    public Topic() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
