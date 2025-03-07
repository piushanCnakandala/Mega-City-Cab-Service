package com.example.megacitycabservice.dao;

import com.example.megacitycabservice.model.AdminUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserDAO {
    private final Connection connection;

    public AdminUserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAdminUser(AdminUser adminUser) {
        String sql = "INSERT INTO admin_user (name, nic, mobile_number, user_name, password) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, adminUser.getName());
            statement.setString(2, adminUser.getNic());
            statement.setString(3, adminUser.getMobileNumber());
            statement.setString(4, adminUser.getUserName());
            statement.setString(5, adminUser.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AdminUser getAdminByUsername(String username) {
        String sql = "SELECT * FROM admin_user WHERE user_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new AdminUser(
                        resultSet.getInt("registration_number"),
                        resultSet.getString("name"),
                        resultSet.getString("nic"),
                        resultSet.getString("mobile_number"),
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