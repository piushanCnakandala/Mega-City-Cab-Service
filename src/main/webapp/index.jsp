<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome - Mega City Cab</title>
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

    .landing-container {
      background: rgba(255, 255, 255, 0.15);
      backdrop-filter: blur(10px);
      padding: 40px;
      border-radius: 15px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
      text-align: center;
      max-width: 500px;
      width: 100%;
      color: white;
    }

    .landing-container h1 {
      font-size: 28px;
      font-weight: 600;
      margin-bottom: 20px;
    }

    .user-options {
      display: flex;
      flex-direction: column;
      gap: 15px;
      margin-top: 20px;
    }

    .user-options a {
      text-decoration: none;
      color: white;
      font-size: 18px;
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

    .user-options a:hover {
      background: rgba(255, 255, 255, 0.3);
    }
  </style>
</head>
<body>

<div class="landing-container">
  <h1>Welcome to Mega City Cab</h1>
  <p>Select your role to continue:</p>
  <div class="user-options">
    <a href="adminlogin.jsp"><i class="fas fa-user-shield"></i> Admin</a>
    <a href="customerlogin.jsp"><i class="fas fa-user"></i> Customer</a>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>