<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.*" %>
<%
  HttpSession s = request.getSession(false);
  if (s == null || s.getAttribute("uid") == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  ResultSet rs = (ResultSet) request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Book Products – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/table.css">
</head>
<body>
  <div class="container">
    <div class="brand">
      <div class="logo">🛒</div>
      <h2>Book Seeds, Fertilizers & Tools</h2>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>Product</th>
            <th>Category</th>
            <th>Price (₹)</th>
            <th>Qty</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <%
            if (rs != null) {
              boolean hasRows = false;
              while (rs.next()) {
                hasRows = true;
          %>
          <tr>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("category") %></td>
            <td>₹ <%= rs.getDouble("price") %></td>
            <td>
              <form action="order" method="post">
                <input type="hidden" name="productId" value="<%= rs.getInt("id") %>">
                <input type="number" name="qty" min="1" value="1" required>
            </td>
            <td>
                <button class="btn-primary" type="submit">Book</button>
              </form>
            </td>
          </tr>
          <%
              }
              if (!hasRows) {
          %>
            <tr>
              <td colspan="5" style="text-align:center; opacity:.8;">
                No products available
              </td>
            </tr>
          <%
              }
            }
          %>
        </tbody>
      </table>
    </div>

    <div class="link">
      <a href="dashboard.jsp">← Back to Dashboard</a>
    </div>
  </div>
</body>
</html>