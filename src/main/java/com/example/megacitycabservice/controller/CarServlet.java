package com.example.megacitycabservice.controller;

import com.example.megacitycabservice.dao.AdminUserDAO;
import com.example.megacitycabservice.dao.CarDAO;
import com.example.megacitycabservice.model.Car;
import com.example.megacitycabservice.service.AdminUserService;
import com.example.megacitycabservice.service.CarService;
import com.example.megacitycabservice.util.DBConnection;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/car")
public class CarServlet extends HttpServlet {
    private  CarService carService;
    public CarServlet(CarService carService) {
        this.carService = carService;
    }

    public CarServlet(){
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            carService = new CarService(new CarDAO(connection));
        } catch (SQLException e) {
            throw new ServletException("Unable to connect to database", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action"); // Determine the action (add, update, delete)

        if ("add".equals(action)) {
            handleAddCar(request, response);
        } else if ("update".equals(action)) {
            handleUpdateCar(request, response);
        } else if ("delete".equals(action)) {
            handleDeleteCar(request, response);
        } else {
            response.sendRedirect("cars.jsp?error=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Car> cars = carService.getAllCars();
            request.setAttribute("car", cars);
            request.getRequestDispatcher("cars.jsp").forward(request, response);
        } catch (SQLException e) {
            response.sendRedirect("cars.jsp?error=1");
        }
    }

    private void handleAddCar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String model = request.getParameter("model");
        String type = request.getParameter("type");
        String year = request.getParameter("year");
        int passengerCount = Integer.parseInt(request.getParameter("passengerCount"));
        String licensePlate = request.getParameter("licensePlate");
        double perKilometerPrice = Double.parseDouble(request.getParameter("perKilometerPrice"));
        double advancePrice = Double.parseDouble(request.getParameter("advancePrice"));
        String status = request.getParameter("status");


        Car car = new Car();
        car.setModel(model);
        car.setType(type);
        car.setYear(year);
        car.setPassengerCount(passengerCount);
        car.setLicensePlate(licensePlate);
        car.setPerKilometerPrice(perKilometerPrice);
        car.setAdvancePrice(advancePrice);
        car.setStatus(status);

        try {
            carService.addCar(car);
            response.sendRedirect("cars?success=1");
        }catch (Exception e){
            response.sendRedirect("cars.jsp?error=1");
        }
    }

    private void handleUpdateCar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        String model = request.getParameter("model");
        String type = request.getParameter("type");
        String year = request.getParameter("year");
        int passengerCount = Integer.parseInt(request.getParameter("passengerCount"));
        String licensePlate = request.getParameter("licensePlate");
        double perKilometerPrice = Double.parseDouble(request.getParameter("perKilometerPrice"));
        double advancePrice = Double.parseDouble(request.getParameter("advancePrice"));
        String status = request.getParameter("Status");

        Car car = new Car();
        car.setModel(model);
        car.setType(type);
        car.setYear(year);
        car.setPassengerCount(passengerCount);
        car.setLicensePlate(licensePlate);
        car.setPerKilometerPrice(perKilometerPrice);
        car.setAdvancePrice(advancePrice);
        car.setStatus(status);

        try {
            carService.updateCar(car);
            response.sendRedirect("cars?success=1");
        } catch (SQLException e) {
            response.sendRedirect("cars?error=1");
        }
    }

    private void handleDeleteCar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int carId = Integer.parseInt(request.getParameter("id"));

        try {
            carService.deleteCar(carId);
            response.sendRedirect("cars?success=1");
        } catch (SQLException e) {
            response.sendRedirect("cars?error=1");
        }
    }
}
