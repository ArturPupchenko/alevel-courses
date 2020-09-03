package com.alevel.courses.jpabox.dao.entityDao.abstractDao;

import java.util.List;

public interface AbstractEntityDao<T> {

    List<T> findAll();

    void saveOrUpdate(T entity);
}
