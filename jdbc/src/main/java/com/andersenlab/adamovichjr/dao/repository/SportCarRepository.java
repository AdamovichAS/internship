package com.andersenlab.adamovichjr.dao.repository;

import com.andersenlab.adamovichjr.model.SportCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportCarRepository extends JpaRepository<SportCarEntity,Integer> {

    SportCarEntity getByIdAndDeletedFalse(int id);


}
