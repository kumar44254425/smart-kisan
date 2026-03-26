<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All India Weather Result – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/weather.css">
</head>
<body>
  <div class="page-center">
    <div class="card">

      <h2>🌦 All India Weather Report</h2>

      <table class="weather-table">
        <tr>
          <th>State</th>
          <th>City</th>
          <th>Temp (°C)</th>
          <th>Weather</th>
          <th>Farmer Alert</th>
          <th>Alert Type</th>
          <th>SMS</th>
          <th>Crop Suggestion</th>
        </tr>

        <%
          List<Map<String,String>> list =
              (List<Map<String,String>>) request.getAttribute("weatherList");

          if (list != null && !list.isEmpty()) {
              for (Map<String,String> w : list) {
                  String alertType = w.get("alertType");
                  if (alertType == null) alertType = "normal";
        %>
          <tr>
            <td><%= w.get("state") %></td>
            <td><%= w.get("city") %></td>
            <td><%= w.get("temp") %></td>
            <td><%= w.get("desc") %></td>
            <td><%= w.get("alert") %></td>   
            <td class="<%= alertType %>"><%= alertType.toUpperCase() %></td>

            <td>
              <form action="send-sms" method="post">
                <input type="hidden" name="mobile" value="919515700891" />
                <input type="hidden" name="message"
                       value="🚨 Smart Kisan Alert for <%= w.get("state") %>: <%= w.get("alert") %>" />
                <button class="sms-btn <%= alertType %>" type="submit">
                  📱 Send SMS
                </button>
              </form>
            </td>
           <td><%= w.get("crop") %></td>
          </tr>
        <%
              }
          } else {
        %>
          <tr>
            <td colspan="7" style="text-align:center; color:#aaa;">
              No data found. Click Fetch button again.
            </td>
          </tr>
        <%
          }
        %>
      </table>

      <div style="margin-top:15px;">
        <a href="weather-india.jsp">← Back</a>
      </div>

    </div>
  </div>
</body>
</html>