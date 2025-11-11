<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Login</title>
  <style>

  body { font-family: Arial, sans-serif; padding: 20px; }
  table { border-collapse: collapse; width: 100%; }
  th, td { border: 1px solid #ddd; padding: 8px; }
  th { background: #f2f2f2; text-align: left; }
  a.btn, button.btn { padding: 6px 10px; border: 1px solid #333; text-decoration: none; cursor: pointer; display:inline-block; background:#fff; }
  .nav { margin-bottom:16px; }
  .nav a { margin-right:10px; }
  .error{color:#b00020;}

    form { max-width: 360px; }
    label { display:block; margin:8px 0; }
  </style>
</head>
<body>
  <h2>Admin Login</h2>
  <c:if test="{{not empty error}}">
    <p class="error">${error}</p>
  </c:if>
  <form method="post" action="${pageContext.request.contextPath}/login">
    <label>Username: <input type="text" name="username" required></label>
    <label>Password: <input type="password" name="password" required></label>
    <button class="btn" type="submit">Login</button>
  </form>
</body>
</html>
