<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
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
  <title>My Orders – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/table.css">
</head>
<body>
  <div class="container">
    <div class="brand">
      <div class="logo">📦</div>
      <h2>My Orders</h2>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>Product</th>
            <th>Category</th>
            <th>Qty</th>
            <th>Price</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          <%
            ResultSet rs = (ResultSet) request.getAttribute("orders");
            boolean hasRows = false;

            if (rs != null) {
              while (rs.next()) {
                hasRows = true;
          %>
            <tr>
              <td><%= rs.getString("product_name") %></td>
              <td><%= rs.getString("category") %></td>
              <td><%= rs.getInt("quantity") %></td>
              <td>₹ <%= rs.getDouble("price") %></td>
              <td><%= rs.getTimestamp("order_date") %></td>
            </tr>
          <%
              }
            }

            if (!hasRows) {
          %>
            <tr>
              <td colspan="5" style="text-align:center; opacity:.7;">
                No orders found 📭
              </td>
            </tr>
          <%
            }
          %>
        </tbody>
      </table>
    </div>

    <div class="link">
      <a href="dashboard.jsp">⬅ Back to Dashboard</a>
    </div>
  </div>
</body>
</html>