package com.alevel.courses.jpabox.entity;

import com.alevel.courses.jpabox.entity.abst.AbstractEntityWithGeneratedId;
import com.alevel.courses.jpabox.entity.embeddable.PersonalInformation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "teachers")
public class Teacher extends AbstractEntityWithGeneratedId {

    @Column(name = "teacher_name")
    private String name;

    @Embedded
    private PersonalInformation personalInformation;

    @ManyToMany(mappedBy = "teachers",fetch = FetchType.EAGER)
    private Collection<Course> courses = new ArrayList<>();

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
