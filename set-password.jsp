<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Set Password – Smart Kisan</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/auth.css">
</head>
<body>
  <div class="page-center">
    <div class="hero-card">
      <h2>Set Password</h2>

      <form action="set-password" method="post">
        <input type="hidden" name="name" value="<%= request.getAttribute("name") %>">
        <input type="hidden" name="mobile" value="<%= request.getAttribute("mobile") %>">
        <input type="password" name="password" placeholder="Create password" required>
        <button class="btn btn-primary" type="submit">Finish Signup</button>
      </form>
    </div>
  </div>
</body>
</html>
