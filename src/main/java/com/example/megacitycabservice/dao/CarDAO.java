package com.example.megacitycabservice.dao;

import com.example.megacitycabservice.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private final Connection connection;

    public CarDAO(Connection connection) {
        this.connection = connection;
    }
    public void add(Car car) {
        String sql = "INSERT INTO car (model, type, year, passenger_count, license_plate,per_kilometer_price,advance_price,status) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getModel());
            statement.setString(2, car.getType());
            statement.setString(3, car.getYear());
            statement.setInt(4, car.getPassengerCount());
            statement.setString(5, car.getLicensePlate());
            statement.setDouble(6,car.getPerKilometerPrice());
            statement.setDouble(7,car.getAdvancePrice());
            statement.setString(8,car.getStatus());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setModel(resultSet.getString("model"));
                car.setType(resultSet.getString("type"));
                car.setYear(resultSet.getString("year"));
                car.setPassengerCount(resultSet.getInt("passenger_count"));
                car.setLicensePlate(resultSet.getString("license_plate"));
                car.setPerKilometerPrice(resultSet.getDouble("per_kilometer_price"));
                car.setAdvancePrice(resultSet.getDouble("advance_price"));
                car.setStatus(resultSet.getString("status"));
                cars.add(car);
            }
        }
        return cars;
    }

    public List<Car> getAllCarsWhereStatus(String status) throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE status = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the status parameter to the provided value
            statement.setString(1, status);

            // Execute the query and process the result
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("id"));
                    car.setModel(resultSet.getString("model"));
                    car.setType(resultSet.getString("type"));
                    car.setYear(resultSet.getString("year"));
                    car.setPassengerCount(resultSet.getInt("passenger_count"));
                    car.setLicensePlate(resultSet.getString("license_plate"));
                    car.setPerKilometerPrice(resultSet.getDouble("per_kilometer_price"));
                    car.setAdvancePrice(resultSet.getDouble("advance_price"));
                    car.setStatus(resultSet.getString("status"));
                    cars.add(car);
                }
            }
        }
        return cars;
    }


    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE car SET model = ?, type = ?, year = ?, passenger_count = ?, license_plate = ?, per_kilometer_price = ?, advance_price = ?, status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getModel());
            statement.setString(2, car.getType());
            statement.setString(3, car.getYear());
            statement.setInt(4, car.getPassengerCount());
            statement.setString(5, car.getLicensePlate());
            statement.setDouble(6, car.getPerKilometerPrice());
            statement.setDouble(7, car.getAdvancePrice());
            statement.setString(8, car.getStatus());
            statement.executeUpdate();
        }
    }

    public void deleteCar(int id) throws SQLException {
        String sql = "DELETE FROM car WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Car getCarById(int id) throws SQLException {
        String query = "SELECT * FROM car WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int carId = rs.getInt("id");
                String model = rs.getString("model");
                String type = rs.getString("type");
                String year = rs.getString("year");
                int passengerCount = rs.getInt("passenger_count");
                String licensePlate = rs.getString("license_plate");
                double price = rs.getDouble("advance_price");
                double perKmPrice = rs.getDouble("per_kilometer_price");
                String status = rs.getString("status");

                return new Car(carId, model,type,year, passengerCount,licensePlate, price,perKmPrice,status);
            }
        }
        return null;  // Return null if the car is not found
    }

    public void updateCarStatus(int carId, String status) throws SQLException {
        String sql = "UPDATE car SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, carId);
            statement.executeUpdate();
        }
    }
    }

