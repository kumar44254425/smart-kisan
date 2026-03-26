<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Crop Profit Calculator</title>
<link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/profit.css">
</head>
<body>
<div class="page-center">
  <div class="card">
    <h2>🌾 Crop Profit Calculator</h2>

    <form action="profit" method="post">
      <input type="text" name="crop" placeholder="Crop Name" required>
      <input type="number" step="0.01" name="area" placeholder="Area (acres)" required>
      <input type="number" step="0.01" name="seedCost" placeholder="Seed Cost (₹)" required>
      <input type="number" step="0.01" name="fertilizerCost" placeholder="Fertilizer Cost (₹)" required>
      <input type="number" step="0.01" name="laborCost" placeholder="Labor Cost (₹)" required>
      <input type="number" step="0.01" name="yield" placeholder="Expected Yield (kg)" required>
      <input type="number" step="0.01" name="marketPrice" placeholder="Market Price (₹ per kg)" required>

      <button class="btn-primary" type="submit">Calculate Profit</button>
    </form>

    <a href="dashboard.jsp">⬅ Back to Dashboard</a>
  </div>
</div>
</body>
</html>