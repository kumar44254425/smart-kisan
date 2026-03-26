<%@ page import="java.util.*" %>
<%
    List<Map<String,Object>> prices =
        (List<Map<String,Object>>) request.getAttribute("prices");
    double bestPrice = (Double) request.getAttribute("bestPrice");
%>

<table border="1" width="80%" align="center">
<tr>
  <th>Crop</th>
  <th>Market</th>
  <th>Price (₹)</th>
  <th>Date</th>
</tr>

<%
for (Map<String,Object> row : prices) {
%>
<tr style="<%= ((Double)row.get("price") == bestPrice) ? "background:#d4f7d4;" : "" %>">
  <td><%= row.get("crop") %></td>
  <td><%= row.get("market") %></td>
  <td>₹ <%= row.get("price") %></td>
  <td><%= row.get("date") %></td>
</tr>
<% } %>
</table>
