package com.example.megacitycabservice.dao;

import com.example.megacitycabservice.model.Booking;
import com.example.megacitycabservice.model.Car;
import com.example.megacitycabservice.model.CustomerUser;
import com.example.megacitycabservice.model.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class BookingDAO {
    private final Connection connection;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO booking (customer_id, starting_meter_km, car_id, driver_id,booking_date,return_date,status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getCustomer().getRegistrationNumber());
            statement.setDouble(2, booking.getStartingMeterKm());
            statement.setInt(3, booking.getCar().getId());
            statement.setInt(4, booking.getDriver().getDriverId());
            statement.setString(5, booking.getBookingDate().format(formatter));
            statement.setString(6, booking.getReturnDate().format(formatter));
            statement.setString(7, booking.getStatus());
            statement.executeUpdate();
        }
    }


        public List<Booking> getAllBookings() throws SQLException {
            List<Booking> bookings = new ArrayList<>();
            String sql = "SELECT * FROM booking";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(resultSet.getInt("booking_id"));
                    booking.setCustomer(new CustomerUser(resultSet.getInt("customer_id")));
                    booking.setStartingMeterKm(resultSet.getDouble("starting_meter_km"));
                    booking.setBookingDate(LocalDate.parse(resultSet.getString("booking_date")));
                    booking.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
                    booking.setTotalAmount(resultSet.getDouble("total_amount"));
                    booking.setStatus(resultSet.getString("status"));
                    booking.setDriver(new Driver(resultSet.getInt("driver_id")));

                    int carId = resultSet.getInt("car_id");
                    booking.setCar(new Car(carId));

                    System.out.println("car " + carId);
                    System.out.println("customer " + booking.getCustomer().toString());

                    bookings.add(booking);
                }
            }
            return bookings;
        }

/*
        private Car getCarByBookingNumber (String bookingNumber) throws SQLException {
            Car car = null;
            String sql = "SELECT car_id FROM booking_cars WHERE booking_number = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, bookingNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String carId = resultSet.getString("car_id");
                        car = new Car(carId);
                    }
                }
            }
            return car;
        }*/


        public void updateBooking (Booking booking) throws SQLException {
            String sql = "UPDATE booking SET starting_meter_km = ?, booking_date = ?, return_date = ?, status = ? WHERE booking_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, booking.getStartingMeterKm());
                statement.setString(2, booking.getBookingDate().format(formatter));
                statement.setString(3, booking.getReturnDate().format(formatter));
                statement.setString(4, booking.getStatus());
                statement.executeUpdate();
            }

        }

        public void deleteBooking (int bookingId) throws SQLException {
            String sql = "DELETE FROM booking WHERE booking_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bookingId);
                statement.executeUpdate();
            }
        }

        public Booking getBookingByNumber (int bookingId) throws SQLException {
            Booking booking = null;
            String sql = "SELECT * FROM bookings WHERE booking_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, bookingId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        booking = new Booking();
                        booking.setBookingId(resultSet.getInt("booking_id"));
                        booking.setStartingMeterKm(resultSet.getDouble("starting_meter_km"));
                        booking.setBookingDate(LocalDate.parse(resultSet.getString("booking_date")));
                        booking.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));

                        // Fetch Customer details
                        int customerId = resultSet.getInt("customer_id");
                        booking.setCustomer(new CustomerUser(customerId));

                        // Fetch Car details
                        int carId = resultSet.getInt("car_id");
                        booking.setCar(new Car(carId));

                        // Fetch Driver details
                        int driverId = resultSet.getInt("driver_id");
                        booking.setDriver(new Driver(driverId));
                    }
                }
            }
            return booking;
        }

}
