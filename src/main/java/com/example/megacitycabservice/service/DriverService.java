package com.example.megacitycabservice.service;

import com.example.megacitycabservice.dao.DriverDAO;
import com.example.megacitycabservice.model.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriverService {
    private final DriverDAO driverDAO;

    public DriverService(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public void addDriver(String name, String licenseNumber, String nic,String status,String availability) throws SQLException {

        Driver driver = new Driver();
        driver.setName(name);
        driver.setLicenseNumber(licenseNumber);
        driver.setNic(nic);
        driver.setStatus(status);
        driver.setAvailability(availability);
        driverDAO.addDriver(driver);
    }

    public List<Driver> getAllDrivers() throws SQLException {
        return driverDAO.getAllDrivers();
    }

    public List<Driver> getAllDriversWhereStatus() throws SQLException {
        return driverDAO.getAllDriversWhereStatus();
    }

    public List<Driver> getAllDriversWhereAvailability() throws SQLException {
            return driverDAO.getAllDriversWhereStatusAndAvailable();
    }

    public void updateDriver(Driver driver) throws SQLException {
        driverDAO.updateDriver(driver);
    }

    public void deleteDriver(String driverId) throws SQLException {
        driverDAO.deleteDriver(driverId);
    }

    public void updateDriverStatus(String driverId, String status) throws SQLException {
        driverDAO.updateDriverStatus(driverId, status);
    }

    public void UpdateDriverAvailability(String driverId, String availability) throws SQLException {
        driverDAO.updateDriverAvailability(driverId, availability);
    }

    public Driver getDriverById(String driverId) throws SQLException {
        return driverDAO.getDriverById(driverId);
    }
}
