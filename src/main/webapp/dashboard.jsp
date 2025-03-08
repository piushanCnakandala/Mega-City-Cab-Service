<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Mega City Cab</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        body {
            background: linear-gradient(to right, #2c3e50, #3498db);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: 'Poppins', sans-serif;
        }

        .dashboard-container {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 600px;
            width: 100%;
            color: white;
            position: relative;
        }

        .dashboard-container h1 {
            font-size: 28px;
            font-weight: 600;
            margin-bottom: 20px;
        }

        .nav-links {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
            margin-top: 20px;
        }

        .nav-links a {
            text-decoration: none;
            color: white;
            font-size: 16px;
            font-weight: 500;
            background: rgba(255, 255, 255, 0.2);
            padding: 12px;
            border-radius: 10px;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
        }

        .nav-links a:hover {
            background: rgba(255, 255, 255, 0.3);
        }

        .logout-btn {
            position: absolute;
            top: 15px;
            right: 20px;
            font-size: 18px;
            color: #ff4757;
            cursor: pointer;
            text-decoration: none;
        }

        .logout-btn:hover {
            color: #ff6b81;
        }
    </style>
</head>
<body>

<div class="dashboard-container">
    <!-- Logout Button -->
    <a href="index.jsp" class="logout-btn" title="Logout">
        <i class="fas fa-sign-out-alt"></i>
    </a>

    <h1>Welcome to Mega City Cab</h1>

    <div class="nav-links">
        <a href="customers"><i class="fas fa-users"></i> Manage Customers</a>
        <a href="bookings"><i class="fas fa-calendar-check"></i> Manage Bookings</a>
        <a href="drivers"><i class="fas fa-id-badge"></i> Manage Drivers</a>
        <a href="cars"><i class="fas fa-car"></i> Manage Cars</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
