package org.example.app.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(car);
            transaction.commit();
            return car;

        } catch (Exception e) {
            if ( transaction.isActive()) transaction.rollback();
            throw new RuntimeException(e);
        }
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
        // TODO HOMEWORK
        // EM.remove(car)
    }

    @Override
    public Car updateCar(Long id, BigDecimal price) {
        // TODO Optional
        return null;
    }

    @Override
    public Car update2(Car car) {
        // TODO HOMEWORK
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
