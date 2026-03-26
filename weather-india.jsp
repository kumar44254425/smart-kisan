<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All India Weather – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/weather.css">
</head>
<body>
  <div class="page-center">
    <div class="card">
      <h2>🇮🇳 All India Weather Report</h2>

      <form action="weather-india" method="post">
        <button class="btn-primary" type="submit">
          Fetch All States Weather
        </button>
      </form>

      <div style="margin-top:15px;">
        <a href="dashboard.jsp">← Back to Dashboard</a>
      </div>
    </div>
  </div>
</body>
</html>