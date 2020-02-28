package com.andersenlab.adamovichjr.dao.impl;

import com.andersenlab.adamovichjr.dao.ICarDao;
import com.andersenlab.adamovichjr.model.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarDaoTest {

    private ICarDao carDao = CarDao.getInstance();

    @Test
    void getInstance() {
        assertNotNull(carDao);
    }

    @Test
    void getById() {
        final Car car = createTestCar();
        final int id = carDao.add(car);
        final Car carFromDB = carDao.getById(id);
        assertNotNull(carFromDB);
        assertEquals(id, carFromDB.getId());
        assertEquals(car.getModel(), carFromDB.getModel());
        assertEquals(car.getColor(), carFromDB.getColor());
        carDao.delete(id);
    }

    @Test
    void add(){
        final Car car = createTestCar();
        int id = carDao.add(car);
        Car addedCar = carDao.getById(id);
        assertEquals(id,addedCar.getId());
        assertEquals(car.getColor(),addedCar.getColor());
        assertEquals(car.getModel(),addedCar.getModel());
        carDao.delete(id);
    }

    @Test
    void delete(){
        final Car car = createTestCar();
        int id = carDao.add(car);
        Car deletedCar = carDao.delete(id);
        assertNotNull(deletedCar);
        Car afterDelete = carDao.getById(id);
        assertNull(afterDelete);
    }

    @Test
    void update(){
        final Car car = createTestCar();
        String colorForUpdate = "orange";
        assertNotEquals(colorForUpdate,car.getColor());
        int id = carDao.add(car);
        car.setId(id);
        car.setColor(colorForUpdate);
        boolean isUpdate = carDao.update(car);
        assertTrue(isUpdate);
        Car updatedCar = carDao.getById(id);
        assertEquals(updatedCar.getColor(),colorForUpdate);
    }

    private Car createTestCar(){
        return new Car("subaru","green");
    }
}
