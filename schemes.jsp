<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Government Schemes Eligibility</title>
  <link rel="stylesheet" href="css/schemes.css">
</head>
<body>
  <div class="page-center">
    <div class="card wide">

      <h2>🌾 Government Schemes Eligibility Checker</h2>

      <form action="check-schemes" method="post" class="form-grid">
        <input type="text" name="passbook" placeholder="Passbook Number" required>
        <input type="text" name="name" placeholder="Farmer Name" required>
        <input type="text" name="state" placeholder="State" required>
        <input type="text" name="district" placeholder="District" required>
        <input type="number" step="0.1" name="land" placeholder="Land (acres)" required>

        <select name="type">
          <option>Small</option>
          <option>Marginal</option>
          <option>Medium</option>
        </select>

        <select name="aadhaar">
          <option value="yes">Has Aadhaar</option>
          <option value="no">No Aadhaar</option>
        </select>

        <select name="bank">
          <option value="yes">Has Bank Account</option>
          <option value="no">No Bank Account</option>
        </select>

        <button class="btn-primary full">✔ Check Eligibility</button>
      </form>

      <a href="dashboard.jsp">← Back</a>

    </div>
  </div>
</body>
</html>