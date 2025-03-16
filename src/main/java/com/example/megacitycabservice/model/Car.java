package com.example.megacitycabservice.model;

public class Car {
    private int id;
    private String model;
    private String type;
    private String year;
    private int passengerCount;
    private String licensePlate;
    private double perKilometerPrice;
    private double advancePrice;
    private String status;

    public Car() {
    }

    public Car(int id, String model,String type,String year,int passengerCount,String licensePlate,double perKilometerPrice,double advancePrice,String status){

        this.id = id;
        this.model = model;
        this.type = type;
        this.year = year;
        this.passengerCount = passengerCount;
        this.licensePlate = licensePlate;
        this.perKilometerPrice = perKilometerPrice;
        this.advancePrice = advancePrice;
        this.status = status;

    }

    public Car(int carId) {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public int getPassengerCount() {
        return passengerCount;
    }
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public double getPerKilometerPrice() {
        return perKilometerPrice;
    }
    public void setPerKilometerPrice(double perKilometerPrice) {
        this.perKilometerPrice = perKilometerPrice;
    }
    public double getAdvancePrice() {
        return advancePrice;
    }
    public void setAdvancePrice(double advancePrice) {
        this.advancePrice = advancePrice;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
