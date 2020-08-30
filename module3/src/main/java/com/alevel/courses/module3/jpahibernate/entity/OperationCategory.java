package com.alevel.courses.module3.jpahibernate.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@MappedSuperclass
public abstract class OperationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OperationCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
