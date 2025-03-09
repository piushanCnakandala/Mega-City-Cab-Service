package com.example.megacitycabservice.dao;

import com.example.megacitycabservice.model.AdminUser;
import com.example.megacitycabservice.model.CustomerUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerUserDAO {
    private final Connection connection;
    public CustomerUserDAO(Connection connection) {
        this.connection = connection;
    }
    public void addCustomer(CustomerUser customerUser) {
        String sql = "INSERT INTO customer_user (name, nic, mobile_number, address, user_name, password) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customerUser.getName());
            statement.setString(2, customerUser.getNic());
            statement.setString(3, customerUser.getMobileNumber());
            statement.setString(4, customerUser.getAddress());
            statement.setString(5, customerUser.getUserName());
            statement.setString(6, customerUser.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerUser getCustomerUserByUsername(String username) {
        String sql = "SELECT * FROM customer_user WHERE user_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new CustomerUser(
                        resultSet.getInt("registration_number"),
                        resultSet.getString("name"),
                        resultSet.getString("nic"),
                        resultSet.getString("mobile_number"),
                        resultSet.getString("address"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

