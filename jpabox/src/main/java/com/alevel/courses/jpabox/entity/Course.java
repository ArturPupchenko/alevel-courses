package com.alevel.courses.jpabox.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "courses")
public class Course extends AbstractEntityWithGeneratedId {

    @Column(nullable = false, unique = true)
    private String name;

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
