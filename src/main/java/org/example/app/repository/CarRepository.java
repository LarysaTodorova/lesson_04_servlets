package org.example.app.repository;

import org.example.app.model.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository {

    List<Car> getAll();

    Car save(Car car);

    Car getById(Long id);

    Car deleteById(Long id);

    void delete (long id);

    Car updateCar(Long id, BigDecimal price);

    Car update2(Car car);

    List<Car> getByPrice(BigDecimal lowPrice, BigDecimal highPrice);

    List<Car> getByBrand(String brand);


}
