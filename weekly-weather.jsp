<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Weekly Weather Trend</title>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <link rel="stylesheet" href="css/weather.css">
</head>
<body>
  <div class="page-center">
    <div class="card">
      <h2>📊 Weekly Weather Trend (Telangana)</h2>

      <canvas id="weatherChart" height="120"></canvas>

      <script>
        const labels = <%= request.getAttribute("labels") %>;
        const temps = <%= request.getAttribute("temps") %>;
        const rain = <%= request.getAttribute("rain") %>;

        new Chart(document.getElementById('weatherChart'), {
          type: 'line',
          data: {
            labels,
            datasets: [
              {
                label: 'Temperature (°C)',
                data: temps,
                borderColor: '#2ecc71',
                fill: false,
                tension: 0.3
              },
              {
                label: 'Rain Days',
                data: rain,
                borderColor: '#ff4d4d',
                fill: false,
                tension: 0.3
              }
            ]
          }
        });
      </script>

      <a href="dashboard.jsp">← Back</a>
    </div>
  </div>
</body>
</html>