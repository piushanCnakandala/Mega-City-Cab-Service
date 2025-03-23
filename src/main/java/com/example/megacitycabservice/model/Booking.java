package com.example.megacitycabservice.model;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private CustomerUser customer;
    private double startingMeterKm;
    private Car car;
    private Driver driver;
    private LocalDate bookingDate;
    private LocalDate returnDate;
    private double totalAmount;
    private String status;

    public Booking() {
    }
    public Booking(int bookingId,CustomerUser customer, double startingMeterKm, Car car, Driver driver, LocalDate bookingDate, LocalDate returnDate,double totalAmount,String status){
        this.bookingId = bookingId;
        this.customer = customer;
        this.startingMeterKm = startingMeterKm;
        this.car = car;
        this.driver = driver;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
        this.totalAmount=totalAmount;
        this.status = status;

    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public CustomerUser getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerUser customer) {
        this.customer = customer;
    }

    public double getStartingMeterKm() {
        return startingMeterKm;
    }
    public void setStartingMeterKm(double startingMeterKm) {
        this.startingMeterKm = startingMeterKm;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

