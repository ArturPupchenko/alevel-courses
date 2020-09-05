package com.alevel.courses.jpabox.entity;

import com.alevel.courses.jpabox.entity.abst.AbstractEntityWithGeneratedId;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends AbstractEntityWithGeneratedId {

    @NaturalId
    @Column(name = "course_name")
    private String name;

    @ManyToMany
    private Collection<Teacher> teachers = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Group> groups = new ArrayList<>();

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<Teacher> teachers) {
        this.teachers = teachers;
    }


    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) this.teachers.add(teacher);
        if (!teacher.getCourses().contains(this)) teacher.getCourses().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        if (groups.contains(teacher)) this.groups.remove(teacher);
        if (teacher.getCourses().contains(this)) teacher.getCourses().remove(this);
    }


    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        if (!groups.contains(group)) this.groups.add(group);
    }

    public void removeGroup(Group group) {
        if (groups.contains(group)) this.groups.remove(group);
    }
}
