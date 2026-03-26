<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*" %>
<%
  HttpSession s = request.getSession(false);
  if (s == null || s.getAttribute("uid") == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Dashboard – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
  <div class="page-center">
    <div class="dash-card">
      <div class="dash-header">
        <div class="logo">🌾</div>
        <div class="welcome">
          <h2>Welcome, <%= s.getAttribute("name") %></h2>
          <p>Your farmer assistant</p>
        </div>
      </div>

      <div class="dash-grid">
        <button class="dash-btn" onclick="location.href='prices'">📈 Market Prices</button>
        <button class="dash-btn" onclick="location.href='products'">🛒 Book Seeds & Fertilizers</button>
        <button class="dash-btn" onclick="location.href='orders'">📦 My Orders</button>
  <button class="dash-btn" onclick="location.href='profit.jsp'">💰 Crop Profit Calculator</button>
<button class="dash-btn" onclick="location.href='weather-india.jsp'">
  🇮🇳 All India Weather
</button>

<button class="dash-btn" onclick="location.href='weekly-weather'">
  📊 Weekly Weather Trend
</button>
<button class="dash-btn" onclick="location.href='schemes.jsp'">
  🏛️ Government Schemes
</button>
        <button class="dash-btn" onclick="location.href='admin.jsp'">🛠 Admin (Daily Updates)</button>
        <button class="dash-btn logout" onclick="location.href='logout'">🚪 Logout</button>
      </div>
    </div>
  </div>
</body>
</html>
