package com.example.megacitycabservice.service;

import com.example.megacitycabservice.dao.CustomerUserDAO;
import com.example.megacitycabservice.model.CustomerUser;

public class CustomerUserService {
    private final CustomerUserDAO customerUserDAO;
    public CustomerUserService(CustomerUserDAO customerUserDAO) {
        this.customerUserDAO = customerUserDAO;
    }

    public void addCustomerUser(CustomerUser customerUser) {
        customerUserDAO.addCustomer(customerUser);
    }
}
