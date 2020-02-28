package com.andersenlab.adamovichjr.dao.impl;

import com.andersenlab.adamovichjr.dao.ICarDao;
import com.andersenlab.adamovichjr.dao.util.DataSource;
import com.andersenlab.adamovichjr.model.Car;

import java.sql.*;

public class CarDao implements ICarDao {
    private DataSource dataSource = DataSource.getInstance();
    private static volatile ICarDao instance;

    public static ICarDao getInstance() {
        ICarDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CarDao();
                }
            }
        }
        return localInstance;
    }


    @Override
    public Car getById(int id) {
        Car car = null;
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement
                     ("SELECT * FROM car WHERE car.id =?;")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                car = new Car();
                car.setId(id);
                car.setModel(resultSet.getString("model"));
                car.setColor(resultSet.getString("color"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fail to save car in data base", e);
        }
        return car;
    }

    @Override
    public int add(Car car) {
        int primaryKey = -1;
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement
                     ("INSERT INTO car (model, color) VALUES (? , ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            final int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                try (final ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    primaryKey = resultSet.getInt(1);
                }
            }
            return primaryKey;
        } catch (SQLException e) {
            throw new RuntimeException("Fail to save car in data base", e);
        }
    }

    @Override
    public boolean update(Car car) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement
                     ("UPDATE car SET model = ?, color = ? WHERE id = ?")) {
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setInt(3, car.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Fail to update car in data base", e);
        }
    }

    @Override
    public Car delete(int id) {
        Car car = getById(id);
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement
                     ("DELETE FROM car WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return car;
        } catch (SQLException e) {
            throw new RuntimeException("Fail to delete car from data base", e);
        }
    }
}
