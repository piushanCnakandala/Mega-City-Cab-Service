<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Management - Mega City Cab</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <style>
        :root {
            --primary-color: #4361ee;
            --secondary-color: #3f37c9;
            --accent-color: #f72585;
            --success-color: #4cc9f0;
            --light-bg: #f8f9fa;
            --dark-text: #212529;
        }

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

        .brand-gradient {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            padding: 15px;
            border-radius: 8px 8px 0 0;
            margin-bottom: 0;
        }

        .card-container {
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            border: none;
        }

        .btn-primary {
            background: rgba(255, 255, 255, 0.2);
            border: none;
        }

        .btn-glass {
            background: rgba(52,152,219, 0.9);
            border: 1px solid rgba(255, 255, 255, 0.4);
            color: white;
            padding: 12px 20px;
            font-size: 18px;
            font-weight: 500;
            border-radius: 10px;
            transition: all 0.3s ease-in-out;
        }

        .btn-primary:hover {
            background: rgba(255, 255, 255, 0.3);
        }

        .btn-success {
            background-color: var(--success-color);
            border-color: var(--success-color);
            color: var(--dark-text);
            font-weight: 600;
        }

        .btn-danger {
            background-color: var(--accent-color);
            border-color: var(--accent-color);
        }
        .table {
            color: white;
            border-radius: 10px;
            overflow: hidden;
        }

        .table th {
            background: rgba(255, 255, 255, 0.2);
        }

        .table-header {
            background-color: var(--primary-color);
            color: white;
        }

        .search-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
            margin-bottom: 20px;
        }

        .home-btn {
            transition: all 0.3s ease;
        }

        .home-btn:hover {
            transform: translateY(-2px);
        }

        .booking-table {
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
        }

        .booking-table th {
            font-weight: 600;
        }
        .table-striped > tbody > tr:nth-of-type(odd) > * {
            background-color: rgba(67, 97, 238, 0.05);
        }

        .action-buttons .btn {
            margin: 0 3px;
            border-radius: 50px;
            padding: 5px 15px;
        }

        .modal-content {
            background: rgba(255, 255, 255, 0.9) !important;
            backdrop-filter: none !important;
            border-radius: 10px;
            z-index: 1050 !important;
        }

        .modal-header {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            border-bottom: none;
        }

        .input-group-text {
            background-color: var(--primary-color);
            color: white;
            border: 1px solid var(--primary-color);
        }

        .modal-backdrop {
            background-color: rgba(0, 0, 0, 0.5) !important;
            z-index: 1040 !important;
        }

        .modal-open {
            overflow: hidden !important;
        }
        /* Toast styling */
        .toast {
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
            border: none;
            border-radius: 8px;
            overflow: hidden;
        }

        .toast-header {
            border-bottom: none;
            padding: 0.75rem 1rem;
        }

        .toast-body {
            padding: 1rem;
        }

        @keyframes highlight {
            0% { background-color: rgba(76, 201, 240, 0.3); }
            100% { background-color: transparent; }
        }

        .new-booking {
            animation: highlight 2s ease-in-out;
        }
    </style>

</head>
<body>
<div class="container mt-5 mb-5">
    <!-- Top Navigation -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <a href="dashboard.jsp" class="btn btn-outline-light">
            <i class="bi bi-house-door-fill"></i> Home
        </a>
    </div>


    <!-- Main Content Card -->
        <h1 class="mb-4">Booking Management</h1>
        <!-- Add New Booking Button -->
        <div class="text-center my-3">
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addBookingModal">
                <i class="bi bi-plus-lg"></i> New Booking
            </button>
        </div>

        <!-- Success Alert -->
        <c:if test="${not empty param.success}">
            <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i> Booking added successfully!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>

            <!-- Additional Toast Notification -->
            <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
                <div id="bookingSuccessToast" class="toast show" role="alert" aria-live="assertive" aria-atomic="true">
                    <div class="toast-header bg-success text-white">
                        <i class="bi bi-check-circle-fill me-2"></i>
                        <strong class="me-auto">Booking Confirmation</strong>
                        <small>Just now</small>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                    <div class="toast-body">
                        <p class="mb-1">Booking #${param.bookingNumber} has been created successfully!</p>
                        <p class="mb-0 small text-muted">The booking details have been saved to the system.</p>
                    </div>
                </div>
            </div>
        </c:if>
        <!-- Booking Table -->
        <div class="table-responsive">
            <table class="table table-dark table-striped text-center">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Customer</th>
                    <th>Starting Meter KM</th>
                    <th>Car</th>
                    <th>Driver</th>
                    <th>Booking Date</th>
                    <th>ReturnDate</th>
                    <th>Amount</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody id="bookingTableBody">
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td class="fw-bold">${booking.bookingId}</td>
                        <td>
                                <span class="badge bg-primary bg-opacity-10 text-primary">
                                        ${booking.customer.registrationNumber}
                                </span>
                        </td>
                        <td>${booking.startingMeterKm}</td>
                        <td>
                                <span class="badge bg-warning bg-opacity-10 text-warning">
                                    ${booking.car.id} - ${booking.car.licensePlate}
                                </span>
                        </td>
                        <td>
                                <span class="badge bg-info bg-opacity-10 text-info">
                                        ${booking.driver.driverId}
                                </span>
                        </td>
                        <td>${booking.bookingDate}</td>
                        <td>${booking.returnDate}</td>
                        <td>${booking.totalAmount}</td>
                        <td>${booking.status}</td>
                        <td class="action-buttons">
                            <a href="deleteBooking?id=${booking.bookingId}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Are you sure you want to delete this booking?')">
                                <i class="bi bi-trash"></i>
                            </a>
                            <a href="bookings?bookingNumber=${booking.bookingId}" class="btn btn-primary btn-sm">
                                <i class="bi bi-printer"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
</div>

<!-- Add Booking Modal -->
<div class="modal fade" id="addBookingModal" tabindex="-1" aria-labelledby="addBookingModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addBookingModalLabel"><i class="bi bi-plus-circle me-2"></i>Add New Booking</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="bookings" method="post" id="bookingForm">
                    <input type="hidden" name="action" value="add">

                    <div class="mb-3">
                        <label for="customerRegistrationNumber" class="form-label"> Customer</label>
                        <select class="form-select" id="customerRegistrationNumber" name="customerRegistrationNumber" required>
                            <option value="" selected disabled>Select a customer</option>
                            <c:forEach var="customer" items="${registeredCustomers}">
                                <option value="${customer.registrationNumber}">${customer.registrationNumber} - ${customer.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="startingMeterKm" class="form-label"> Starting Meter KM</label>
                        <input type="text" class="form-control" id="startingMeterKm" name="startingMeterKm" required>
                    </div>

                    <div class="mb-3">
                        <label for="id" class="form-label">Select Car</label>
                        <select class="form-select" id="id" name="id" required>
                            <option value="" selected disabled>Select a car</option>
                            <c:forEach var="car" items="${availableCars}">
                                <option value="${car.id}"> - ${car.model} (${car.licensePlate})</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="driverId" class="form-label"> Select Driver</label>
                        <select class="form-select" id="driverId" name="driverId" required>
                            <option value="" selected disabled>Select a driver</option>
                            <c:forEach var="driver" items="${availableDrivers}">
                                <option value="${driver.driverId}">${driver.driverId} - ${driver.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <<div class="mb-3">
                    <label for="bookingDate" class="form-label"> Booking Date</label>
                    <input type="date" class="form-control" id="bookingDate" name="bookingDate" required>
                </div>

                    <div class="mb-3">
                        <label for="returnDate" class="form-label"> Return Date</label>
                        <input type="date" class="form-control" id="returnDate" name="returnDate" required>
                    </div>

                    <div class="mb-3">
                        <label for="status" class="form-label">Status</label>
                        <input type="text" class="form-control" id="status" name="status" required>
                    </div>


                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-glass">
                            <i class="bi bi-check-circle me-2"></i> Add Booking
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript -->
<script>
    // Search function
    function searchTable() {
        let bookingInput = document.getElementById("searchBooking").value.toLowerCase();
        let customerInput = document.getElementById("searchCustomer").value.toLowerCase();
        let table = document.getElementById("bookingTableBody");
        let rows = table.getElementsByTagName("tr");

        for (let i = 0; i < rows.length; i++) {
            let bookingNumber = rows[i].getElementsByTagName("td")[0].innerText.toLowerCase();
            let customerId = rows[i].getElementsByTagName("td")[1].innerText.toLowerCase();

            if (bookingNumber.includes(bookingInput) && customerId.includes(customerInput)) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
    function calculateFare() {
        const distance = parseFloat(document.getElementById("distance").value);
        if (!isNaN(distance)) {
            // Base fare + per km rate (sample calculation)
            const baseFare = 300;
            const perKmRate = 80;
            const estimatedFare = baseFare + (distance * perKmRate);
            document.getElementById("fare").value = Math.round(estimatedFare);
        } else {
            document.getElementById("fare").value = "";
        }
    }

    // Toast initialization
    document.addEventListener('DOMContentLoaded', function() {
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('success')) {
            // Show the toast
            const toast = new bootstrap.Toast(document.getElementById('bookingSuccessToast'), {
                autohide: true,
                delay: 5000
            });
            toast.show();

            // Highlight the newly added booking
            const bookingNumber = urlParams.get('bookingNumber');
            if (bookingNumber) {
                const rows = document.getElementById("bookingTableBody").getElementsByTagName("tr");
                for (let i = 0; i < rows.length; i++) {
                    const id = rows[i].getElementsByTagName("td")[0].innerText;
                    if (id === bookingNumber) {
                        rows[i].classList.add('new-booking');
                        // Scroll to the new booking
                        rows[i].scrollIntoView({ behavior: 'smooth', block: 'center' });
                        break;
                    }
                }
            }
        }
    });
</script>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
