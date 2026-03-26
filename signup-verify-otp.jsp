<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Verify OTP – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/auth.css">
</head>
<body>
  <div class="page-center">
    <div class="hero-card">
      <h2>Verify OTP</h2>

      <p style="text-align:center;">Demo OTP: <b><%= request.getAttribute("demoOtp") %></b></p>

      <form action="signup-verify-otp" method="post">
        <input type="hidden" name="name" value="<%= request.getAttribute("name") %>">
        <input type="hidden" name="mobile" value="<%= request.getAttribute("mobile") %>">
        <input name="otp" placeholder="Enter OTP" required>
        <button class="btn btn-primary" type="submit">Verify</button>
      </form>
    </div>
  </div>
</body>
</html>
