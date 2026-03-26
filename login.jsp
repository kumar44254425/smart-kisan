<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/auth.css">
</head>
<body>
  <div class="page-center">
    <div class="hero-card">
      <h2>Login to Smart Kisan</h2>
      <% if (request.getAttribute("error") != null) { %>
     <div class="error"><%= request.getAttribute("error") %></div>
       <% } %>
      <!-- Password Login -->
      <form action="login" method="post">
        <input name="mobile" placeholder="Mobile number" pattern="[6-9][0-9]{9}" required>
        <input type="password" name="password" placeholder="Password" required>
        <button class="btn btn-primary" type="submit">Login with Password</button>
      </form>

      <div class="or">OR</div>

      <!-- OTP Login -->
      <form action="request-otp" method="post">
        <input name="mobile" placeholder="Mobile number" pattern="[6-9][0-9]{9}" required>
        <button class="btn btn-secondary" type="submit">Login with OTP</button>
      </form>

      <div class="link">
        New user? <a href="signup.jsp">Create account</a>
      </div>
     <div class="link">
  <a href="forgot-password.jsp">Forgot password?</a>
     </div>

    </div>
  </div>
</body>
</html>
