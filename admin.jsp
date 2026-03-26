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
  <title>Admin – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/admin.css">
</head>
<body>
  <div class="page-center">
    <div class="admin-card">
      <div class="admin-header">
        <div class="logo">🛠</div>
        <div>
          <h2>Admin — Market Prices</h2>
          <p>Add manually or fetch today’s live mandi prices</p>
        </div>
      </div>

      <!-- Add Manual Price -->
      <form action="<%=request.getContextPath()%>/admin/add-price" method="post" class="admin-form">
        <input name="crop" placeholder="Crop name (e.g., Tomato)" required>
        <input name="market" placeholder="Market name (e.g., Kothapet)" required>
        <input name="price" type="number" step="0.01" placeholder="Price per kg (₹)" required>
        <button class="btn-primary" type="submit">Add Today’s Price</button>
      </form>

      <!-- Fetch Live Prices -->
      <form action="<%=request.getContextPath()%>/admin/fetch-live-prices" method="post" style="margin-top: 12px;">
        <button class="btn-primary" type="submit">
          Fetch Today’s Live Market Prices
        </button>
      </form>

      <div class="back">
        <a href="dashboard.jsp">← Back to Dashboard</a>
      </div>
    </div>
  </div>
</body>
</html>
