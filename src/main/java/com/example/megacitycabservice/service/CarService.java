package com.example.megacitycabservice.service;

import com.example.megacitycabservice.dao.CarDAO;
import com.example.megacitycabservice.model.Car;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    private final CarDAO carDAO;
    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }
    public void addCar(Car car) {
        carDAO.add(car);
    }

    public List<Car> getAllCars() throws SQLException {
        return carDAO.getAllCars();
    }

    public List<Car> getAllCarsWhereStatus(String status) throws SQLException {
        return carDAO.getAllCarsWhereStatus(status);
    }

    public void updateCar(Car car) throws SQLException {
        carDAO.updateCar(car);
    }

    public void deleteCar(int id) throws SQLException {
        carDAO.deleteCar(id);
    }

    public Car getCarById(int carId)throws SQLException {
        return carDAO.getCarById(carId);
    }
    public void updateCarStatus(int carId, String status) throws SQLException {
        carDAO.updateCarStatus(carId, status);
    }
}
