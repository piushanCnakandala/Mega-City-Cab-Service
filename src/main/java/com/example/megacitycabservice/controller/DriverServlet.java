package com.example.megacitycabservice.controller;

import com.example.megacitycabservice.dao.DriverDAO;
import com.example.megacitycabservice.model.Driver;
import com.example.megacitycabservice.service.DriverService;
import com.example.megacitycabservice.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/drivers")
public class DriverServlet extends HttpServlet {

    private DriverService driverService;

    public DriverServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            driverService = new DriverService(new DriverDAO(connection));
        } catch (SQLException e) {
            throw new ServletException("Unable to connect to database", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            handleAddDriver(request, response);
        } else if ("update".equals(action)) {
            handleUpdateDriver(request, response);
        } else if ("delete".equals(action)) {
            handleDeleteDriver(request, response);
        } else {
            response.sendRedirect("drivers.jsp?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Driver> drivers = driverService.getAllDrivers();
            request.setAttribute("drivers", drivers);
            request.getRequestDispatcher("drivers.jsp").forward(request, response);
        } catch (SQLException e) {
            response.sendRedirect("drivers.jsp?error=1");
        }
    }

    private void handleAddDriver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenseNumber");
        String nic = request.getParameter("nic");
        String status = request.getParameter("status");
        String availability = request.getParameter("availability");

        try {
            driverService.addDriver(name, licenseNumber, nic, status, availability);
            response.sendRedirect("drivers?success=1");
        } catch (SQLException e) {
            response.sendRedirect("drivers?error=1");
        }
    }

    private void handleUpdateDriver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenseNumber");
        String nic = request.getParameter("nic");
        String status = request.getParameter("status");
        String availability = request.getParameter("availability");

        Driver driver = new Driver();
        driver.setName(name);
        driver.setLicenseNumber(licenseNumber);
        driver.setNic(nic);
        driver.setStatus(status);
        driver.setAvailability(availability);

        try {
            driverService.updateDriver(driver);
            response.sendRedirect("drivers?success=1");
        } catch (SQLException e) {
            response.sendRedirect("drivers?error=1");
        }
    }

    private void handleDeleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String driverId = request.getParameter("driverId");

        try {
            driverService.deleteDriver(driverId);
            response.sendRedirect("drivers?success=1");
        } catch (SQLException e) {
            response.sendRedirect("drivers?error=1");
        }
    }
}
