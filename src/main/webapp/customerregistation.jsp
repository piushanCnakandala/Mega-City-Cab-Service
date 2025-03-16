<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Registration - Mega City Cab</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            background: linear-gradient(to right, #2c3e50, #3498db);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: 'Poppins', sans-serif;
        }

        .register-container {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 400px;
            width: 100%;
            color: white;
        }

        .register-container h2 {
            margin-bottom: 10px;
            font-weight: 600;
        }

        .register-container p {
            font-size: 14px;
            margin-bottom: 20px;
        }

        .form-control {
            background: rgba(255, 255, 255, 0.2);
            border: none;
            color: white;
            padding: 12px;
            border-radius: 25px;
            outline: none;
        }

        .form-control::placeholder {
            color: rgba(255, 255, 255, 0.7);
        }

        .form-control:focus {
            background: rgba(255, 255, 255, 0.3);
            box-shadow: none;
        }

        .input-group-text {
            background: transparent;
            border: none;
            color: white;
        }

        .btn-register {
            width: 100%;
            padding: 12px;
            border-radius: 25px;
            background-color: #27ae60;
            color: white;
            font-weight: 600;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-register:hover {
            background-color: #2ecc71;
        }

        .login-link {
            margin-top: 15px;
            font-size: 14px;
        }

        .login-link a {
            color: #f1c40f;
            font-weight: 500;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Customer Registration</h2>
    <p>Join Mega City Cab Service</p>

    <form action="customer" method="post">
        <input type="hidden" name="action" value="add">

        <div class="mb-3 input-group">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
            <input type="text" class="form-control" id="name" name="name" placeholder="Full Name" required>
        </div>

        <div class="mb-3 input-group">
            <span class="input-group-text"><i class="fas fa-id-card"></i></span>
            <input type="text" class="form-control" id="nic" name="nic" placeholder="NIC" required>
        </div>

        <div class="mb-3 input-group">
            <span class="input-group-text"><i class="fas fa-phone"></i></span>
            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="Mobile Number" required>
        </div>

        <div class="mb-3 input-group">
            <span class="input-group-text"><i class="fas fa-phone"></i></span>
            <input type="text" class="form-control" id="address" name="address" placeholder="Address" required>
        </div>

        <div class="mb-3 input-group">
            <span class="input-group-text"><i class="fas fa-user-circle"></i></span>
            <input type="text" class="form-control" id="username" name="userName" placeholder="Username" required>
        </div>

        <div class="mb-3 input-group">
            <span class="input-group-text"><i class="fas fa-lock"></i></span>
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
        </div>

        <button type="submit" class="btn btn-register">Register</button>
    </form>

    <p class="login-link">Already have an account? <a href="customerlogin.jsp">Login here</a></p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    const success = urlParams.get('success');

    if (error === '1') {
        alert("Registration failed. Please check your details.");
    }

    if (success === '1') {
        alert("Registration successful! Redirecting...");
        setTimeout(() => {
            window.location.href = 'customerlogin.jsp';
        }, 2000);
    }
</script>

</body>
</html>