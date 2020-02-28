package com.andersenlab.adamovichjr.dao;

import com.andersenlab.adamovichjr.model.Car;

public interface ICarDao {

    public Car getById(int id);
    public int add(Car car);
    public boolean update(Car car);
    public Car delete(int id);
}
