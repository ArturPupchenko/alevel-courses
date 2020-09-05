package com.alevel.courses.jpabox.entity;

import com.alevel.courses.jpabox.entity.abst.AbstractEntityWithGeneratedId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "topics")
public class Topic extends AbstractEntityWithGeneratedId {

    @Column(name = "topic_name")
    private String name;

    @ManyToMany
    @JoinColumn(name = "course_id", nullable = false)
    private Collection<Course> courses = new ArrayList<>();

    public Topic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
