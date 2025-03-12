package com.example.megacitycabservice.dao;

import com.example.megacitycabservice.enums.UserStatus;
import com.example.megacitycabservice.model.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    private final Connection connection;

    public DriverDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDriver(Driver driver) throws SQLException {
        String sql = "INSERT INTO driver ( name, license_number, nic , status, availability) VALUES (?, ?, ?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.setString(3, driver.getNic());
            statement.setString(4, driver.getStatus());
            statement.setString(5, driver.getAvailability());
            statement.executeUpdate();
        }
    }

    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM driver";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setDriverId(resultSet.getInt("driver_id"));
                driver.setName(resultSet.getString("name"));
                driver.setLicenseNumber(resultSet.getString("license_number"));
                driver.setNic(resultSet.getString("nic"));
                driver.setStatus(resultSet.getString("status"));
                driver.setAvailability(resultSet.getString("availability"));
                drivers.add(driver);
            }
        }
        return drivers;
    }
    public List<Driver> getAllDriversWhereStatus() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM driver WHERE status = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, UserStatus.AVAILABLE.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Driver driver = new Driver();
                    driver.setDriverId(resultSet.getInt("driver_id"));
                    driver.setName(resultSet.getString("name"));
                    driver.setLicenseNumber(resultSet.getString("license_number"));
                    driver.setNic(resultSet.getString("nic"));
                    drivers.add(driver);
                }
            }
        }
        return drivers;
    }

    public List<Driver> getAllDriversWhereStatusAndAvailable() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM driver WHERE status = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, UserStatus.AVAILABLE.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Driver driver = new Driver();
                    driver.setDriverId(resultSet.getInt("driver_id"));
                    driver.setName(resultSet.getString("name"));
                    driver.setLicenseNumber(resultSet.getString("license_number"));
                    driver.setNic(resultSet.getString("nic"));
                    drivers.add(driver);
                }
            }
        }
        return drivers;
    }


    public void updateDriver(Driver driver) throws SQLException {
        String sql = "UPDATE driver SET name = ?, license_number = ? , nic = ? , status = ? , availability = ? WHERE driver_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.setString(3, driver.getNic());
            statement.setString(4, driver.getStatus());
            statement.setString(5, driver.getAvailability());
            statement.executeUpdate();
        }
    }

    public void deleteDriver(String driverId) throws SQLException {
        String sql = "DELETE FROM driver WHERE driver_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, driverId);
            statement.executeUpdate();
        }
    }

    public void updateDriverStatus(String driverId, String status) throws SQLException {
        String sql = "UPDATE driver SET status = ? WHERE driver_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setString(2, driverId);
            statement.executeUpdate();
        }
    }

    public void updateDriverAvailability(String driverId, String status) throws SQLException {
        String sql = "UPDATE driver SET availability = ? WHERE driver_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setString(2, driverId);
            statement.executeUpdate();
        }
    }

    public Driver getDriverById(String driverId) throws SQLException {
        String query = "SELECT * FROM driver WHERE driver_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("driver_id");
                String name = rs.getString("name");
                String licenseNumber = rs.getString("license_number");
                String nic = rs.getString("nic");
                String status = rs.getString("status");
                String availability = rs.getString("availability");

                return new Driver(id, name, licenseNumber, nic, status, availability);
            }
        }
        return null;  // Return
    }

}
