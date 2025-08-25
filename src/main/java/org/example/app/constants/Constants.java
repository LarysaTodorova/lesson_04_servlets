package org.example.app.constants;

public interface Constants {

    // http://localhost:8080/cars?id=5
    // jdbc:postgresql://localhost:5432/g_51_cars?user=my_user&password=pos1234

    String DB_DRIVER_PATH = "org.postgresql.Driver";
    String DB_ADDRESS = "jdbc:postgresql://localhost:5432/";
    String DB_NAME = "g_51_cars";
    String DB_USER = "my_user";
    String DB_PASSWORD = "pos1234";
}
