<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Forgot Password – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/auth.css">
</head>
<body>
  <div class="page-center">
    <div class="hero-card">
      <h2>Reset Password</h2>

      <form action="request-otp" method="post">
        <input name="mobile" placeholder="Mobile number" pattern="[6-9][0-9]{9}" required>
        <button class="btn btn-primary">Send OTP</button>
      </form>
    </div>
  </div>
</body>
</html>
