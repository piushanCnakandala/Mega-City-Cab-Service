package com.example.megacitycabservice.service;

import com.example.megacitycabservice.dao.BookingDAO;
import com.example.megacitycabservice.model.Booking;

import java.sql.SQLException;
import java.util.List;

public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public void addBooking(Booking booking) throws SQLException {
        bookingDAO.addBooking(booking);
    }

    public List<Booking> getAllBookings() throws SQLException {
        return bookingDAO.getAllBookings();
    }

    public void updateBooking(Booking booking) throws SQLException {
        bookingDAO.updateBooking(booking);
    }

    public void deleteBooking(int bookingId) throws SQLException {
        bookingDAO.deleteBooking(bookingId);
    }

    public Booking getBookingByNumber(int bookingNumber) throws SQLException {
        return bookingDAO.getBookingByNumber(bookingNumber);
    }

}
