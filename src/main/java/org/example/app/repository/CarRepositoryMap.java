package org.example.app.repository;

import org.example.app.model.Car;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CarRepositoryMap implements CarRepository {

    private Map<Long, Car> dataBase = new HashMap<>();
    private long currentId;

    public CarRepositoryMap() {
        initData();
    }

    private void initData() {
        save(new Car("VW", new BigDecimal(15000), 2015));
        save(new Car("Mazda", new BigDecimal(30000), 2022));
        save(new Car("Ford", new BigDecimal(40000), 2025));
    }


    @Override
    public List<Car> getAll() {
        return dataBase.values().stream().toList();
        // return new ArrayList<>(dataBase.values());
    }

    @Override
    public Car save(Car car) {
        car.setId(++currentId);
        dataBase.put(car.getId(), car);
        return car;
    }

    @Override
    public Car getById(Long id) {
        return dataBase.getOrDefault(id, null);
    }

    @Override
    public Car deleteById(Long id) {
        return dataBase.remove(id);
    }

    @Override
    public void delete(long id) {
        dataBase.remove(id);
    }

    @Override
    public Car updateCar(Long id, BigDecimal price) {
//        Car carForUpdate = getById(id);
//        if (carForUpdate == null) {
//            System.out.println("Car with id " + id + " not found");
//            return null;
//        }
//        carForUpdate.setPrice(price);
//        return carForUpdate;

        // получить авто из БД
        Car car = dataBase.getOrDefault(id, null);

        if (car != null) {
            // изменить цену
            car.setPrice(price);

            // вернуть обновленный автомобиль
            return car;
        }
        return null;
    }

    @Override
    public Car update2(Car car) {
        // TODO Homework
        return null;
    }

    @Override
    public List<Car> getByPrice(BigDecimal lowPrice, BigDecimal highPrice) {
        return dataBase.values().stream()
                .filter(car -> car.getPrice().compareTo(lowPrice) >= 0
                        && car.getPrice().compareTo(highPrice) <= 0)
                .toList();
    }

    @Override
    public List<Car> getByBrand(String brand) {
        return dataBase.values().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand.trim()))
                .toList();

    }
}
