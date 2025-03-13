package com.example.megacitycabservice.dao;

import com.example.megacitycabservice.model.AdminUser;
import com.example.megacitycabservice.model.CustomerUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public List<CustomerUser> getAllCustomers() throws SQLException {
        List<CustomerUser> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer_user";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                CustomerUser customer = new CustomerUser();
                customer.setRegistrationNumber(resultSet.getInt("registration_number"));
                customer.setName(resultSet.getString("name"));
                customer.setNic(resultSet.getString("nic"));
                customer.setMobileNumber(resultSet.getString("mobile_number"));
                customer.setAddress(resultSet.getString("address"));
                customers.add(customer);
            }
        }
        return customers;
    }

    public void updateCustomer(CustomerUser customer) throws SQLException {
        String sql = "UPDATE customer_user SET name = ?, nic = ?, mobile_number = ?, address = ? WHERE registration_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getNic());
            statement.setString(4, customer.getMobileNumber());
            statement.executeUpdate();
        }
    }

    public void deleteCustomer(int registrationNumber) throws SQLException {
        String sql = "DELETE FROM customer_user WHERE registration_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, registrationNumber);
            statement.executeUpdate();
        }
    }

    public CustomerUser getCustomerByRegistrationNumber(int registrationNumber) throws SQLException {
        String query = "SELECT * FROM customer_user WHERE registration_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, registrationNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CustomerUser customer = new CustomerUser();
                    customer.setRegistrationNumber(rs.getInt("registration_number"));
                    customer.setName(rs.getString("name"));
                    customer.setNic(rs.getString("nic"));
                    customer.setMobileNumber(rs.getString("mobileNumber"));
                    customer.setAddress(rs.getString("address"));

                    return customer;
                } else {
                    return null;  // Return null if no customer found
                }
            }
        }
    }
}

