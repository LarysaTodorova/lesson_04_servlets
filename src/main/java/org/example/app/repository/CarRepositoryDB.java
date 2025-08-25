package org.example.app.repository;

import org.example.app.constants.Constants;
import org.example.app.model.Car;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.List;

import static org.example.app.constants.Constants.*;

public class CarRepositoryDB implements CarRepository {

    private Connection getConnection() {

        try {
            Class.forName(Constants.DB_DRIVER_PATH);

            String dbUrl = String.format("%s%s?user=%s&password=%s",
                    Constants.DB_ADDRESS, DB_NAME, DB_USER, DB_PASSWORD);

            return DriverManager.getConnection(dbUrl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Car> getAll() {
        return List.of();
    }

    @Override
    public Car save(Car car) {
        return null;
    }

    @Override
    public Car getById(Long id) {
        return null;
    }

    @Override
    public Car deleteById(Long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Car updateCar(Long id, BigDecimal price) {
        return null;
    }

    @Override
    public Car update2(Car car) {
        return null;
    }

    @Override
    public List<Car> getByPrice(BigDecimal lowPrice, BigDecimal highPrice) {
        return List.of();
    }

    @Override
    public List<Car> getByBrand(String brand) {
        return List.of();
    }
}
