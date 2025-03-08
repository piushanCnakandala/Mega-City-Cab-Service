package com.example.megacitycabservice.service;

import com.example.megacitycabservice.dao.AdminUserDAO;
import com.example.megacitycabservice.model.AdminUser;

public class AdminUserService {
    private final AdminUserDAO adminUserDAO;

    public AdminUserService(AdminUserDAO adminUserDAO) {
        this.adminUserDAO = adminUserDAO;
    }
    public void  addAdminUser(AdminUser adminUser) {
        adminUserDAO.addAdminUser(adminUser);

    }
}
