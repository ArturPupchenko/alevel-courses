package com.alevel.courses.jpabox.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntityWithGeneratedId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    public Long getId() {
        return id;
    }
}
