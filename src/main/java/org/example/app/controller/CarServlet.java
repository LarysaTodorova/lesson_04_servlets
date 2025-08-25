package org.example.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.model.Car;
import org.example.app.repository.CarRepository;
import org.example.app.repository.CarRepositoryDB;
import org.example.app.repository.CarRepositoryMap;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CarServlet extends HttpServlet {

    private CarRepository repository = new CarRepositoryDB();
    ObjectMapper mapper = new ObjectMapper();

    //  GET http://localhost:8080/cars
    //  GET http://localhost:8080/cars?id=3 - один авто по id

    // получение инфомации о cars -> метод GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // cars -> должны вернуть все авто из БД

        /*
        request - это объект запроса. Из него мы можем извлечь все , что прислал клиент
        response - это объект ответа, который будет отправлен клиенту , после того, как отработает наш код.
        В объект response мы можем поместить всю информацию , которую мы хотим отправить клиенту,
        в ответ на его запрос( request )
         */

        //  /example?id=5&name=cat
        // { "id": ["5"], "name":["Cat]}

        Map<String, String[]> params = req.getParameterMap();
        //  GET http://localhost:8080/cars?id=3
        //  "id": ["3"]

        // устанавливаю тип контента для ответа в JSON
        resp.setContentType("application/json");


        if (params.isEmpty()) {

            // вернуть все машины
            List<Car> cars = repository.getAll();

            // преобразую список машин в JSON
            String jsonResponse = mapper.writeValueAsString(cars);
            resp.getWriter().write(jsonResponse);

//            cars.forEach(c -> {
//                try {
//                    resp.getWriter().write(c.toString() + "\n");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
        } else if (params.containsKey("id")) {
            long id = Long.parseLong(params.get("id")[0]);
            Car car = repository.getById(id);
            resp.getWriter().write(car == null ? "Car not found" : mapper.writeValueAsString(car));

        } else if (params.containsKey("lowPrice") && params.containsKey("highPrice")) {
            BigDecimal lowPrice = new BigDecimal(params.get("lowPrice")[0]);
            BigDecimal highPrice = new BigDecimal(params.get("highPrice")[0]);
            List<Car> cars = repository.getByPrice(lowPrice, highPrice);
            String jsonResponse = mapper.writeValueAsString(cars);
            resp.getWriter().write(jsonResponse);

        } else if (params.containsKey("brand")) {
            String brand = params.get("brand")[0];
            List<Car> cars = repository.getByBrand(brand);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            String jsonResponse = mapper.writeValueAsString(cars);
            resp.getWriter().write(jsonResponse);
        }

    }

    // POST/cars - в теле запроса будет приходить объект автомобиля
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //для сохранения нового автомобиля в базу данных


        Car car = mapper.readValue(req.getReader(), Car.class);
        Car savedCar = repository.save(car);

        resp.setContentType("application/json");
        String jsonResponse = mapper.writeValueAsString(savedCar);
        resp.getWriter().write(jsonResponse);
    }

    // PUT/ cars
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO homework
        // для изменения существующего автомобиля в БД
        // Должна поменяться только цена существующего автомобиля
        // id, newPrice

//        Car car = mapper.readValue(req.getReader(), Car.class);
//
//        Car updatedCar = repository.updateCar(car.getId(), car.getPrice());
//        resp.setContentType("application/json");
//
//        if (updatedCar == null) {
//            resp.getWriter().write("Car with id " + car.getId() + " not found");
//        } else {
//            String jsonResponse = mapper.writeValueAsString(updatedCar);
//            resp.getWriter().write(jsonResponse);
//        }

        resp.setContentType("application/json");

        long id = Long.parseLong(req.getParameter("id"));
        String priceStr = req.getParameter("price");

        Car updatedCar = repository.updateCar(id, new BigDecimal(priceStr));

        String json = mapper.writeValueAsString(updatedCar);
        resp.getWriter().write(json);
    }

    //DELETE/cars
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO homework
        // для удаления авто из БД по id

//        String idParam = req.getParameter("id");
//        if (idParam == null) {
//            resp.getWriter().write("Missing id parameter");
//            return;
//        }
//
//        long id = Long.parseLong(idParam);
//
//        Car deletedCar = repository.deleteById(id);
//
//        resp.setContentType("application/json");
//
//        if (deletedCar == null) {
//            resp.getWriter().write("Car with id " + id + " not found");
//        } else {
//
//            String jsonResponse = mapper.writeValueAsString(deletedCar);
//            resp.getWriter().write(jsonResponse);
//        }

        long id = Long.parseLong(req.getParameter("id"));
        repository.delete(id);
    }
}
