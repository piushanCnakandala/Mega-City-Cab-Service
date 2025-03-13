package com.example.megacitycabservice.service;

import com.example.megacitycabservice.dao.CustomerUserDAO;
import com.example.megacitycabservice.model.CustomerUser;

import java.sql.SQLException;
import java.util.List;

public class CustomerUserService {
    private final CustomerUserDAO customerUserDAO;
    public CustomerUserService(CustomerUserDAO customerUserDAO) {
        this.customerUserDAO = customerUserDAO;
    }

    public void addCustomerUser(CustomerUser customerUser) {
        customerUserDAO.addCustomer(customerUser);
    }
    public List<CustomerUser> getAllCustomers() throws SQLException {
        return customerUserDAO.getAllCustomers();
    }

    public void updateCustomer(String name, String address, String nic, String mobileNumber) throws SQLException {

        CustomerUser customer = new CustomerUser();
        customer.setName(name);
        customer.setNic(nic);
        customer.setMobileNumber(mobileNumber);
        customer.setAddress(address);
        customerUserDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int registrationNumber) throws SQLException {
        customerUserDAO.deleteCustomer(registrationNumber);
    }

    public CustomerUser getCustomerByRegistrationNumber(int customerId) throws SQLException {
        return customerUserDAO.getCustomerByRegistrationNumber(customerId);
    }

}
