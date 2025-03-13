<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <style>
        body {
            background: linear-gradient(to right, #2c3e50, #3498db);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            font-family: 'Poppins', sans-serif;
        }

        .container {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            color: white;
            width: 100%;
            max-width: 900px;
        }

        h1, h2 {
            text-align: center;
            font-weight: 600;
        }
        .btn-primary {
            background: rgba(255, 255, 255, 0.2);
            border: none;
        }

        .btn-primary:hover {
            background: rgba(255, 255, 255, 0.3);
        }

        .table {
            color: white;
            border-radius: 10px;
            overflow: hidden;
        }

        .table th {
            background: rgba(255, 255, 255, 0.2);
        }

        .alert {
            text-align: center;
            font-weight: 500;
        }

        .modal-content {
            background: rgba(255, 255, 255, 0.9);
            backdrop-filter: blur(10px);
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Home Button -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <a href="dashboard.jsp" class="btn btn-outline-light">
            <i class="bi bi-house-door-fill"></i> Home
        </a>
    </div>

    <h1>Customer Management</h1>

    <!-- Success and Error Alerts -->
    <div id="successAlert" class="alert alert-success d-none" role="alert">✅ Operation completed successfully!</div>
    <div id="errorAlert" class="alert alert-danger d-none" role="alert">❌ Error occurred while processing your request.</div>

    <!-- Add New Customer Button -->
    <div class="text-center my-3">
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCustomerModal">
            <i class="bi bi-plus-lg"></i> Add New Customer
        </button>
    </div>

    <!-- Customer List -->
    <h2 class="mt-4">Customer List</h2>
    <div class="table-responsive">
        <table class="table table-dark table-striped text-center">
            <thead>
            <tr>
                <th>Registration Number</th>
                <th>Name</th>
                <th>NIC</th>
                <th>Mobile Number</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.registrationNumber}</td>
                <td>${customer.name}</td>
                <td>${customer.nic}</td>
                <td>${customer.mobileNumber}</td>
                <td>${customer.address}</td>
                <td>
                    <!-- Update Button -->
                    <button class="btn btn-warning btn-sm" data-bs-toggle="modal"
                            data-bs-target="#updateCustomerModal${customer.registrationNumber}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                    <!-- Delete Button -->
                    <form action="customer" method="post" class="d-inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="registrationNumber" value="${customer.registrationNumber}">
                        <button type="submit" class="btn btn-danger btn-sm"><i class="bi bi-trash"></i></button>
                    </form>
                </td>
            </tr>

            <!-- Update Modal -->
            <div class="modal fade" id="updateCustomerModal${customer.registrationNumber}" tabindex="-1" aria-labelledby="updateCustomerModalLabel${customer.registrationNumber}" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateCustomerModalLabel${customer.registrationNumber}">Update Customer</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="customer" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="registrationNumber" value="${customer.registrationNumber}">
                                <div class="mb-3">
                                    <label class="form-label">Name</label>
                                    <input type="text" name="name" class="form-control" value="${customer.name}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">NIC</label>
                                    <input type="text" name="nic" class="form-control" value="${customer.nic}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Mobile Number</label>
                                    <input type="text" name="mobileNumber" class="form-control" value="${customer.mobileNumber}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Address</label>
                                    <input type="text" name="address" class="form-control" value="${customer.address}" required>
                                </div>
                                <button type="submit" class="btn btn-success w-100">Update Customer</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Add Customer Modal -->
<div class="modal fade" id="addCustomerModal" tabindex="-1" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addCustomerModalLabel">Add New Customer</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="customer" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">NIC</label>
                        <input type="text" class="form-control" name="nic" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mobile Number</label>
                        <input type="text" class="form-control" name="mobileNumber" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Address</label>
                        <input type="text" class="form-control" name="address" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">User Name</label>
                        <input type="text" class="form-control" name="userName" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="text" class="form-control" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Register Customer</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    window.onload = () => {
        if (new URLSearchParams(window.location.search).has("success")) {
            document.getElementById('successAlert').classList.remove('d-none');
        }
    };
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>