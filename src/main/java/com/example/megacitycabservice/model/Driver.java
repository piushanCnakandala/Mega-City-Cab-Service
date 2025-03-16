package com.example.megacitycabservice.model;

public class Driver {
    private int driverId;
    private String name;
    private String licenseNumber;
    private  String nic;
    private String status;
    private String availability;
    public Driver(int driverId, String name, String licenseNumber, String nic, String status, String availability) {
        this.driverId = driverId;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.nic = nic;
        this.status = status;
        this.availability = availability;
    }

    public Driver() {

    }

    public Driver(int driverId) {
    }

    public int getDriverId() {
        return driverId;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public String getNic() {
        return nic;
    }
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
