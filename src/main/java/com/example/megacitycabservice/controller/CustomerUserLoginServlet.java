package com.example.megacitycabservice.controller;

import com.example.megacitycabservice.dao.AdminUserDAO;
import com.example.megacitycabservice.dao.CustomerUserDAO;
import com.example.megacitycabservice.model.AdminUser;
import com.example.megacitycabservice.model.CustomerUser;
import com.example.megacitycabservice.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login-customer")
public class CustomerUserLoginServlet extends HttpServlet {

    private CustomerUserDAO customerUserDAO;

    @Override
    public void init() throws ServletException {
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            customerUserDAO = new CustomerUserDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Unable to connect to database", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CustomerUser customerUser = customerUserDAO.getCustomerUserByUsername(username);

        if (customerUser != null && customerUser.getPassword().equals(password)) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}