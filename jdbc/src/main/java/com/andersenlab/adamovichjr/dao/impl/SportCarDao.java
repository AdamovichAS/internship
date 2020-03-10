package com.andersenlab.adamovichjr.dao.impl;

import com.andersenlab.adamovichjr.dao.CrudDao;

import com.andersenlab.adamovichjr.dao.repository.SportCarRepository;
import com.andersenlab.adamovichjr.model.SportCarEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SportCarDao implements CrudDao<SportCarEntity> {

    private final SportCarRepository repository;

    public SportCarDao(SportCarRepository repository) {
        this.repository = repository;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SportCarEntity getById(int id) {
        return repository.getByIdAndDeletedFalse(id);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public int add(SportCarEntity car) {
        car = repository.save(car);
        return car.getId();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean update(SportCarEntity car) {
        Session session = entityManager.unwrap(Session.class);
        int updatedParameters = session.createSQLQuery("UPDATE sport_car SET model=:model, color=:color, price=:price, max_speed=:maxSpeed ")
                .setParameter("model", car.getModel())
                .setParameter("color", car.getColor())
                .setParameter("price", car.getPrice())
                .setParameter("maxSpeed", car.getMaxSpeed()).executeUpdate();
        session.getTransaction().commit();
        return updatedParameters > 0;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public SportCarEntity delete(int id) {
        SportCarEntity sportCarEntity = repository.getOne(id);
        sportCarEntity.setDeleted(true);
        repository.flush();
        return sportCarEntity;
    }
}
