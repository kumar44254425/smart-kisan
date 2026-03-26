<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Eligibility Results</title>
  <link rel="stylesheet" href="css/schemes.css">
</head>
<body>
  <div class="page-center">
    <div class="card wide">

      <h2>✅ Eligibility Results</h2>

      <table class="weather-table">
        <tr>
          <th>Scheme</th>
          <th>Status</th>
          <th>Benefits</th>
        </tr>

        <%
          List<Map<String,String>> list =
              (List<Map<String,String>>) request.getAttribute("schemes");

          if (list != null && !list.isEmpty()) {
            for (Map<String,String> r : list) {
        %>
        <tr>
          <td><%= r.get("scheme") %></td>
          <td><%= r.get("status") %></td>
          <td><%= r.get("benefit") %></td>
        </tr>
        <% 
            }
          } else {
        %>
        <tr>
          <td colspan="3" style="text-align:center;color:#aaa;">
            No results found. Please submit form again.
          </td>
        </tr>
        <% } %>

      </table>

      <a href="schemes.jsp">← Back</a>

    </div>
  </div>
</body>
</html>