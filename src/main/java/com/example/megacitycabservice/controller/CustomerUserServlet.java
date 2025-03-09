package com.example.megacitycabservice.controller;

import com.example.megacitycabservice.dao.AdminUserDAO;
import com.example.megacitycabservice.dao.CustomerUserDAO;
import com.example.megacitycabservice.model.AdminUser;
import com.example.megacitycabservice.model.CustomerUser;
import com.example.megacitycabservice.service.AdminUserService;
import com.example.megacitycabservice.service.CustomerUserService;
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

@WebServlet("/customer")
public class CustomerUserServlet extends HttpServlet {
   private CustomerUserService customerUserService;

    public CustomerUserServlet() {
        super();
    }

   public CustomerUserServlet(CustomerUserService customerUserService) {
       this.customerUserService = customerUserService;

   }
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            customerUserService = new CustomerUserService(new CustomerUserDAO(connection));
        } catch (SQLException e) {
            throw new ServletException("Unable to connect to database", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action"); // Determine the action (add, update, delete)

        if ("add".equals(action)) {
            handleAddCustomerUser(request, response);
        } else {
            response.sendRedirect("customer.jsp?error=1");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("customerlogin.jsp");
    }

    private void handleAddCustomerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String nic = request.getParameter("nic");
        String mobileNumber = request.getParameter("mobileNumber");
        String address = request.getParameter("address");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

       CustomerUser customerUser = new CustomerUser();
        customerUser.setName(name);
        customerUser.setNic(nic);
        customerUser.setMobileNumber(mobileNumber);
        customerUser.setUserName(userName);
        customerUser.setAddress(address);
        customerUser.setPassword(password);


        try {
            customerUserService.addCustomerUser(customerUser);
            response.sendRedirect("customer?success=1");
        } catch (IOException e) {
            response.sendRedirect("customer?error=1");
        }
    }
}
