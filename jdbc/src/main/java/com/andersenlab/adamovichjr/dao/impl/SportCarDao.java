package com.andersenlab.adamovichjr.dao.impl;

import com.andersenlab.adamovichjr.dao.CrudDao;
import com.andersenlab.adamovichjr.dao.util.HibernateUtil;
import com.andersenlab.adamovichjr.model.CarEntity;
import com.andersenlab.adamovichjr.model.SportCarEntity;
import org.hibernate.Session;

import java.sql.*;

public class SportCarDao implements CrudDao<SportCarEntity> {

    private static volatile CrudDao instance;

    public static CrudDao getInstance() {
        CrudDao localInstance = instance;
        if (localInstance == null) {
            synchronized (SportCarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SportCarDao();
                }
            }
        }
        return localInstance;
    }


    @Override
    public SportCarEntity getById(int id) {
//        try (final Connection connection = dataSource.getConnection();
////             final PreparedStatement statement = connection.prepareStatement
////                     ("SELECT * FROM car WHERE car.id =? AND car.is_deleted = false;")) {
////            statement.setInt(1, id);
////            ResultSet resultSet = statement.executeQuery();
////            while (resultSet.next()) {
////                car = new CarEntity();
////                car.setId(id);
////                car.setModel(resultSet.getString("model"));
////                car.setColor(resultSet.getString("color"));
////                car.setDeleted(resultSet.getBoolean("is_deleted"));
//////                String created_at = resultSet.getString("created_at");
//////                car.setCreatedAt(LocalDateTime.parse(created_at));
////                car.setCreatedAt(resultSet.getTimestamp("created_at"));
////            }
////        } catch (SQLException e) {
////            throw new RuntimeException("Fail to save car in data base", e);
////        }
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SportCarEntity sportCarEntity = (SportCarEntity) session.createQuery("FROM SportCarEntity s WHERE s.id=:id AND s.deleted = false ")
                .setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return sportCarEntity;
    }

    @Override
    public int add(SportCarEntity car) {
//        int primaryKey = -1;
//        try (final Connection connection = dataSource.getConnection();
//             final PreparedStatement statement = connection.prepareStatement
//                     ("INSERT INTO car (model, color,is_deleted,created_at) VALUES (? , ?, ?,?)",
//                             Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, car.getModel());
//            statement.setString(2, car.getColor());
//            statement.setBoolean(3,car.isDeleted());
//            statement.setTimestamp(4,car.getCreatedAt());
//            final int rowsUpdated = statement.executeUpdate();
//            if (rowsUpdated > 0) {
//                try (final ResultSet resultSet = statement.getGeneratedKeys()) {
//                    resultSet.next();
//                    primaryKey = resultSet.getInt(1);
//                }
//            }
//            return primaryKey;
//        } catch (SQLException e) {
//            throw new RuntimeException("Fail to save car in data base", e);
//        }
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(car);
        session.getTransaction().commit();
        return car.getId();
    }

    @Override
    public boolean update(SportCarEntity car) {
//        try (final Connection connection = dataSource.getConnection();
////             final PreparedStatement statement = connection.prepareStatement
////                     ("UPDATE car SET model = ?, color = ? WHERE id = ?")) {
////            statement.setString(1, car.getModel());
////            statement.setString(2, car.getColor());
////            statement.setInt(3, car.getId());
////            return statement.executeUpdate() > 0;
////        } catch (SQLException e) {
////            throw new RuntimeException("Fail to update car in data base", e);
////        }
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        int updatedParameters = session.createSQLQuery("UPDATE sport_car SET model=:model, color=:color, price=:price, max_speed=:maxSpeed ")
                .setParameter("model", car.getModel())
                .setParameter("color", car.getColor())
                .setParameter("price", car.getPrice())
                .setParameter("maxSpeed", car.getMaxSpeed()).executeUpdate();
        session.getTransaction().commit();
        return updatedParameters > 0;
    }

    @Override
    public SportCarEntity delete(int id) {
//        CarEntity car = getById(id);
//        try (final Connection connection = dataSource.getConnection();
//             final PreparedStatement statement = connection.prepareStatement
//                     ("UPDATE car SET is_deleted = true WHERE id = ?")) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            car.setDeleted(true);
//            return car;
//        } catch (SQLException e) {
//            throw new RuntimeException("Fail to delete car from data base", e);
//        }
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        int updatedParameters = session.createQuery("UPDATE SportCarEntity s SET s.deleted=true WHERE s.id=:id")
                .setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        if(updatedParameters > 0){
            session.getTransaction().begin();
            SportCarEntity sportCarEntity = session.find(SportCarEntity.class, id);
            session.getTransaction().commit();
            return sportCarEntity;
        }
        return null;
    }
}
