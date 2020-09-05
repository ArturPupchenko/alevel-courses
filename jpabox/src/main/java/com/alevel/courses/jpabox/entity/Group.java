package com.alevel.courses.jpabox.entity;

import com.alevel.courses.jpabox.entity.abst.AbstractEntityWithGeneratedId;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "groups")
public class Group extends AbstractEntityWithGeneratedId {

    @NaturalId
    @Column(name = "group_name")
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Collection<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "groups")
    private Collection<Course> courses;

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.setGroup(this);
    }

    public void removeStudent(Student student) {
        if (student.getGroup().getName().equals(this.getName())) {
            this.students.remove(student);
            student.setGroup(null);
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + getId() +
                "name=" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
