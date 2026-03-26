<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.DecimalFormat" %>
<%
  DecimalFormat df = new DecimalFormat("0.00");
  double profit = (Double) request.getAttribute("profit");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Profit Result</title>
<link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/profit.css">
</head>
<body>
<div class="page-center">
  <div class="card">
    <h2>📊 Profit Result</h2>

    <div class="result">
      <p><b>Crop:</b> <%= request.getAttribute("crop") %></p>
      <p><b>Area:</b> <%= df.format(request.getAttribute("area")) %> acres</p>
      <p><b>Total Cost:</b> ₹<%= df.format(request.getAttribute("totalCost")) %></p>
      <p><b>Revenue:</b> ₹<%= df.format(request.getAttribute("revenue")) %></p>

      <p class="<%= profit >= 0 ? "profit" : "loss" %>">
        <b>Profit / Loss:</b> ₹<%= df.format(profit) %>
      </p>
    </div>

    <a href="profit.jsp">🔁 Calculate Again</a><br>
    <a href="dashboard.jsp">🏠 Dashboard</a>
  </div>
</div>
</body>
</html>