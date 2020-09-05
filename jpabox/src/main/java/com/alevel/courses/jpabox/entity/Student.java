package com.alevel.courses.jpabox.entity;

import com.alevel.courses.jpabox.entity.abst.AbstractEntityWithGeneratedId;
import com.alevel.courses.jpabox.entity.embeddable.PersonalInformation;

import javax.persistence.*;


@Entity
@Table(name = "students")
public class Student extends AbstractEntityWithGeneratedId {

    @Column(name = "student_name")
    private String name;

    @Embedded
    private PersonalInformation personalInformation;

    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;

    public Student() {
        super();
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        group.getStudents().add(this);
    }
}