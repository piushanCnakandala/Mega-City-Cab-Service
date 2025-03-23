package com.example.megacitycabservice.controller;

import com.example.megacitycabservice.dao.AdminUserDAO;
import com.example.megacitycabservice.model.AdminUser;
import com.example.megacitycabservice.service.AdminUserService;
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

@WebServlet("/admin")
public class AdminUserServlet extends HttpServlet {

    private AdminUserService adminUserService;

    public AdminUserServlet() {
        super();
    }

    public AdminUserServlet(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            adminUserService = new AdminUserService(new AdminUserDAO(connection));
        } catch (SQLException e) {
            throw new ServletException("Unable to connect to database", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action"); // Determine the action (add, update, delete)

        if ("add".equals(action)) {
            handleAddAdminUser(request, response);
        } else {
            response.sendRedirect("admin.jsp?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("adminlogin.jsp");
    }

    private void handleAddAdminUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String nic = request.getParameter("nic");
        String mobileNumber = request.getParameter("mobileNumber");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        AdminUser adminUser = new AdminUser();
        adminUser.setName(name);
        adminUser.setNic(nic);
        adminUser.setMobileNumber(mobileNumber);
        adminUser.setUserName(userName);
        adminUser.setPassword(password);


        try {
            adminUserService.addAdminUser(adminUser);
            response.sendRedirect("admin?success=1");
        } catch (IOException e) {
            response.sendRedirect("admin?error=1");
        }
    }

}
