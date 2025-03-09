<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Management - Mega City Cab</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
        btn-secondary {
            background: linear-gradient(to right, #2c3e50, #3498db);
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

    <h1>Car Management</h1>
    <!-- Success and Error Alerts -->
    <div id="successAlert" class="alert alert-success d-none" role="alert">✅ Operation completed successfully!</div>
    <div id="errorAlert" class="alert alert-danger d-none" role="alert">❌ Error occurred while processing your request.</div>

    <!-- Add New Car Button -->
    <div class="text-center my-3">
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCarModal">
            <i class="bi bi-plus-lg"></i> Add New Car
        </button>
    </div>

    <!-- Car List -->
    <h2 class="mt-4">Car List</h2>
    <div class="table-responsive">
        <table class="table table-dark table-striped text-center">
            <thead>
            <tr>
                <th>ID</th>
                <th>Model</th>
                <th>Type</th>
                <th>Year</th>
                <th>Seats</th>
                <th>License</th>
                <th>Price/km</th>
                <th>Advance</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${cars}">
            <tr>
                <td>${car.carId}</td>
                <td>${car.model}</td>
                <td>${car.type}</td>
                <td>${car.year}</td>
                <td>${car.passengerCount}</td>
                <td>${car.licensePlate}</td>
                <td>${car.perKilometerPrice}</td>
                <td>${car.advancePrice}</td>
                <td>${car.status}</td>
                <td>
                    <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateCarModal${car.carId}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                    <form action="car" method="post" class="d-inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="carId" value="${car.carId}">
                        <button type="submit" class="btn btn-danger btn-sm"><i class="bi bi-trash"></i></button>
                    </form>
                </td>
            </tr>

            <!-- Update Modal -->
            <div class="modal fade" id="updateCarModal${car.carId}" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Update Car</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form action="car" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="carId" value="${car.carId}">
                                <div class="mb-3">
                                    <label class="form-label">Model</label>
                                    <input type="text" name="model" class="form-control" value="${car.model}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">License Plate</label>
                                    <input type="text" name="licensePlate" class="form-control" value="${car.licensePlate}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Price/km</label>
                                    <input type="text" name="perKilometerPrice" class="form-control" value="${car.perKilometerPrice}" required>
                                </div>
                                <button type="submit" class="btn btn-success w-100">Update Car</button>
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

<!-- Add Car Modal -->
<div class="modal fade" id="addCarModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add New Car</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form action="car" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="mb-3">
                        <label class="form-label">Model</label>
                        <input type="text" class="form-control" name="model" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Type</label>
                        <input type="text" class="form-control" name="type" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Year</label>
                        <input type="number" class="form-control" name="year" min="1900" max="2099" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Passenger Count</label>
                        <input type="number" class="form-control" name="passengerCount" min="1" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">License Plate</label>
                        <input type="text" class="form-control" name="licensePlate" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Per Kilometer Price</label>
                        <input type="number" class="form-control" name="perKilometerPrice" min="0" step="0.01" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Advance Price</label>
                        <input type="number" class="form-control" name="advancePrice" min="0" step="0.01" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select class="form-control" name="status" required>
                            <option value="Active">Active</option>
                            <option value="Inactive">Inactive</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-secondary w-100">Add Car</button>
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