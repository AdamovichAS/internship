package com.andersenlab.adamovichjr.dao.impl;

import com.andersenlab.adamovichjr.config.DaoConfig;
import com.andersenlab.adamovichjr.config.HibernateConfig;
import com.andersenlab.adamovichjr.dao.CrudDao;
import com.andersenlab.adamovichjr.model.CarEntity;
import com.andersenlab.adamovichjr.model.SportCarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class})
@Transactional()
//@Rollback(value = false)
public class CarDaoTest {

    @Autowired
    private CrudDao<SportCarEntity> carDao;


    @Test
    void getById() {
        final SportCarEntity expected = createTestCar();
        final int expectedId = carDao.add(expected);
        SportCarEntity actual = carDao.getById(expectedId);
        assertNotNull(actual);
        assertEquals(expectedId, actual.getId());
        assertEquals(expected.getModel(), expected.getModel());
        assertEquals(expected.getColor(), actual.getColor());
        assertNotNull(actual.getCreatedAt());
        assertFalse(actual.isDeleted());
        assertEquals(actual.getMaxSpeed(),expected.getMaxSpeed());
        assertEquals(actual.getPrice(),expected.getPrice());
        carDao.delete(expectedId);
    }

    @Test
    void add(){
        final SportCarEntity expected = createTestCar();
        int expectedId = carDao.add(expected);
        SportCarEntity actual = carDao.getById(expectedId);
        assertEquals(expectedId,actual.getId());
        assertEquals(expected.getColor(),actual.getColor());
        assertEquals(expected.getModel(),actual.getModel());
        assertNotNull(actual.getCreatedAt());
        assertFalse(actual.isDeleted());
        assertEquals(actual.getMaxSpeed(),expected.getMaxSpeed());
        assertEquals(actual.getPrice(),expected.getPrice());
        carDao.delete(expectedId);
    }

    @Test
    void delete(){
        final SportCarEntity expected = createTestCar();
        int expectedId = carDao.add(expected);
        SportCarEntity actual = carDao.delete(expectedId);
        assertEquals(expectedId,actual.getId());
        assertEquals(expected.getColor(),actual.getColor());
        assertEquals(expected.getModel(),actual.getModel());
        assertNotNull(actual.getCreatedAt());
        assertTrue(actual.isDeleted());
        assertEquals(actual.getMaxSpeed(),expected.getMaxSpeed());
        assertEquals(actual.getPrice(),expected.getPrice());
    }

    @Test
    void update(){
        final SportCarEntity expected = createTestCar();
        String expectedColor = "orange";
        assertNotEquals(expectedColor,expected.getColor());
        int expectedId = carDao.add(expected);
        expected.setId(expectedId);
        expected.setColor(expectedColor);
        boolean isUpdated = carDao.update(expected);
        assertTrue(isUpdated);
        SportCarEntity actual = carDao.getById(expectedId);
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getColor(),actual.getColor());
        assertEquals(expected.getModel(),actual.getModel());
        assertNotNull(actual.getCreatedAt());
        assertFalse(actual.isDeleted());
        assertEquals(actual.getMaxSpeed(),expected.getMaxSpeed());
        assertEquals(actual.getPrice(),expected.getPrice());
    }

    private SportCarEntity createTestCar(){
        SportCarEntity sportCarEntity = new SportCarEntity();
        sportCarEntity.setMaxSpeed(300);
        sportCarEntity.setPrice(200000);
        sportCarEntity.setColor("red");
        sportCarEntity.setDeleted(false);
        sportCarEntity.setModel("Porsche");
        return sportCarEntity;
    }
}
