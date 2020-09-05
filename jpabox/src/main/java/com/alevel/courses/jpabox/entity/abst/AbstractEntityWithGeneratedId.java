package com.alevel.courses.jpabox.entity.abst;


import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntityWithGeneratedId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}