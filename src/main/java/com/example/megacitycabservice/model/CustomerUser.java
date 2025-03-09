package com.example.megacitycabservice.model;

public class CustomerUser {
    private int registrationNumber;
    private String name;
    private String nic;
    private String mobileNumber;
    private String address;
    private String userName;
    private String password;

    public CustomerUser() {
    }

    public CustomerUser(int registrationNumber, String name, String nic, String mobileNumber, String address, String userName, String password) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.userName = userName;
        this.password = password;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNic() {
        return nic;
    }
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
