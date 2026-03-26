<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Signup – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/auth.css">
</head>
<body>
  <div class="page-center">
    <div class="hero-card">
      <h2>Create Account</h2>

    <form action="request-otp" method="post">
        <input name="name" placeholder="Full name" required>
        <input name="mobile" placeholder="Mobile number" pattern="[6-9][0-9]{9}" required>
        <button class="btn btn-primary" type="submit">Verify Phone</button>
      </form>

      <div class="link">
        Already registered? <a href="login.jsp">Login</a>
      </div>
    </div>
  </div>
</body>
</html>
