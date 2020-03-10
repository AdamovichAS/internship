package com.andersenlab.adamovichjr.dao;

import com.andersenlab.adamovichjr.model.CarEntity;

public interface CrudDao<T> {

    public T getById(int id);
    public int add(T obj);
    public boolean update(T obj);
    public T delete(int id);
}
