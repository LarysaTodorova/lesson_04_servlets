package org.example.app.repository;

import org.example.app.constants.Constants;
import org.example.app.model.Car;

import java.math.BigDecimal;
import java.sql.*;

import java.util.List;

import static org.example.app.constants.Constants.*;

public class CarRepositoryDB implements CarRepository {

    private Connection getConnection() {

        try {
            Class.forName(Constants.DB_DRIVER_PATH);
            String dbUrl = String.format("%s%s?user=%s&password=%s",
                    DB_ADDRESS, DB_NAME, DB_USER, DB_PASSWORD
            );


            return DriverManager.getConnection(dbUrl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Car> getAll() {
        // TODO HOMEWORK
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return List.of();
    }

    @Override
    public Car save(Car car) {

        try (Connection connection = getConnection()) {

            // insert into car(brand, price, year) values ('Toyota', 35000, 2024);
            String query = String.format("INSERT INTO car(brand, price, year) values ('%s', %s, %d)",
                    car.getBrand(), car.getPrice(), car.getYear());

            Statement statement = connection.createStatement();

            // execute() - для внесения изменений в БД;
            // executeQuery() - для получения данных;
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);

            // Достать id  из Statement
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next(); // переключаемся на первую колонку

            Long id = resultSet.getLong("id");
            car.setId(id);
            return car;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car getById(Long id) {
        try (Connection connection = getConnection()) {

            String query = String.format("SELECT * FROM car WHERE id = %d;", id);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // пытаемся переключиться на первую колонку, если она есть - вернется true
            if (resultSet.next()) {
                String brand = resultSet.getString("brand");
                BigDecimal price = resultSet.getBigDecimal("price");
                int year = resultSet.getInt("year");

                return new Car(id, brand, price, year);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car deleteById(Long id) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void delete(long id) {
        // TODO HOMEWORK
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car updateCar(Long id, BigDecimal price) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Car update2(Car car) {
        // TODO HOMEWORK
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Car> getByPrice(BigDecimal lowPrice, BigDecimal highPrice) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return List.of();
    }

    @Override
    public List<Car> getByBrand(String brand) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return List.of();
    }

    public void test(Car car) {
        try (Connection connection = getConnection()) {

            String sql = "INSERT INTO car(brand, price, year) VALUES (?, ?, ?);";

            PreparedStatement ps = connection.prepareStatement(sql);

            // Подставляем значения вместо ?
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getPrice().toString());
            ps.setInt(3, car.getYear());

            // int rowsInserted = ps.executeUpdate();
            ResultSet resultSet = ps.executeQuery();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
