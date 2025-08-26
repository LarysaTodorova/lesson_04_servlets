package org.example.app.repository;

import jakarta.persistence.EntityManager;
import org.example.app.model.Car;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.List;

public class CarRepositoryHibernate implements CarRepository {

    private EntityManager entityManager;

    public CarRepositoryHibernate() {
        entityManager = new Configuration()
                .configure("hibernate/postgres.cfg.xml")
                .buildSessionFactory().createEntityManager();
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
        return entityManager.find(Car.class, id);
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
        // TODO Optional
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
