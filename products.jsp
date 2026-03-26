<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
  <title>Book Products – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
</head>
<body>

<div class="container">
  <h2>🛒 Book Seeds, Fertilizers & Tools</h2>

  <table class="weather-table">
    <tr>
      <th>Product</th>
      <th>Category</th>
      <th>Price (₹)</th>
      <th>Qty</th>
      <th>Action</th>
    </tr>

    <tr>
      <form action="order" method="post">
        <td>Tomato Seeds (Hybrid)</td>
        <td>Seed</td>
        <td>120</td>
        <td>
          <input type="number" name="qty" value="1" min="1" required>
        </td>
        <td>
          <input type="hidden" name="productId" value="1">
          <button class="btn-primary" type="submit">Book</button>
        </td>
      </form>
    </tr>

    <tr>
      <form action="order" method="post">
        <td>Urea Fertilizer 50kg</td>
        <td>Fertilizer</td>
        <td>900</td>
        <td>
          <input type="number" name="qty" value="1" min="1" required>
        </td>
        <td>
          <input type="hidden" name="productId" value="2">
          <button class="btn-primary" type="submit">Book</button>
        </td>
      </form>
    </tr>

    <tr>
      <form action="order" method="post">
        <td>Neem Pesticide 1L</td>
        <td>Pesticide</td>
        <td>350</td>
        <td>
          <input type="number" name="qty" value="1" min="1" required>
        </td>
        <td>
          <input type="hidden" name="productId" value="3">
          <button class="btn-primary" type="submit">Book</button>
        </td>
      </form>
    </tr>

    <tr>
      <form action="order" method="post">
        <td>Hand Sprayer</td>
        <td>Tool</td>
        <td>750</td>
        <td>
          <input type="number" name="qty" value="1" min="1" required>
        </td>
        <td>
          <input type="hidden" name="productId" value="4">
          <button class="btn-primary" type="submit">Book</button>
        </td>
      </form>
    </tr>

  </table>

  <a href="dashboard.jsp">⬅ Back</a>
</div>

</body>
</html>