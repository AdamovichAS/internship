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
        final Car expected = createTestCar();
        final int expectedId = carDao.add(expected);
        final Car actual = carDao.getById(expectedId);
        assertNotNull(actual);
        assertEquals(expectedId, actual.getId());
        assertEquals(expected.getModel(), expected.getModel());
        assertEquals(expected.getColor(), actual.getColor());
        assertNotNull(actual.getCreatedAt());
        assertFalse(actual.isDeleted());
        carDao.delete(expectedId);
    }

    @Test
    void add(){
        final Car expected = createTestCar();
        int expectedId = carDao.add(expected);
        Car actual = carDao.getById(expectedId);
        assertEquals(expectedId,actual.getId());
        assertEquals(expected.getColor(),actual.getColor());
        assertEquals(expected.getModel(),actual.getModel());
        assertNotNull(actual.getCreatedAt());
        assertFalse(actual.isDeleted());
        carDao.delete(expectedId);
    }

    @Test
    void delete(){
        final Car expected = createTestCar();
        int expectedId = carDao.add(expected);
        Car actual = carDao.delete(expectedId);
        assertEquals(expectedId,actual.getId());
        assertEquals(expected.getColor(),actual.getColor());
        assertEquals(expected.getModel(),actual.getModel());
        assertNotNull(actual.getCreatedAt());
        assertTrue(actual.isDeleted());
    }

    @Test
    void update(){
        final Car expected = createTestCar();
        String expectedColor = "orange";
        assertNotEquals(expectedColor,expected.getColor());
        int expectedId = carDao.add(expected);
        expected.setId(expectedId);
        expected.setColor(expectedColor);
        boolean isUpdated = carDao.update(expected);
        assertTrue(isUpdated);
        Car actual = carDao.getById(expectedId);
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getColor(),actual.getColor());
        assertEquals(expected.getModel(),actual.getModel());
        assertNotNull(actual.getCreatedAt());
        assertFalse(actual.isDeleted());
    }

    private Car createTestCar(){
        return new Car("subaru","green",false);
    }
}
