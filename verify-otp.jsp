<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String flow = (String) request.getAttribute("flow"); // "login" or "signup"
%>
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
      <h2>Enter OTP</h2>

      <p style="text-align:center; opacity:.85;">
        Demo OTP: <b><%= request.getAttribute("demoOtp") %></b>
      </p>

      <form action="verify-otp" method="post">
        <input type="hidden" name="mobile" value="<%= request.getAttribute("mobile") %>">
        <input type="hidden" name="flow" value="<%= flow %>">
        <% if ("signup".equals(flow)) { %>
          <input type="hidden" name="name" value="<%= request.getAttribute("name") %>">
        <% } %>

        <input name="otp" placeholder="Enter OTP" required>
        <button class="btn btn-primary" type="submit">
          Verify OTP
        </button>
      </form>
    </div>
  </div>
</body>
</html>
