<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Members</title>
  <style>
  body { font-family: Arial, sans-serif; padding: 20px; }
  table { border-collapse: collapse; width: 100%; }
  th, td { border: 1px solid #ddd; padding: 8px; }
  th { background: #f2f2f2; text-align: left; }
  a.btn, button.btn { padding: 6px 10px; border: 1px solid #333; text-decoration: none; cursor: pointer; display:inline-block; background:#fff; }
  .nav { margin-bottom:16px; }
  .nav a { margin-right:10px; }
  .error{color:#b00020;}
</style>
</head>
<body>
  <div class="nav">
    <a href="${pageContext.request.contextPath}/dashboard" class="btn">Dashboard</a>
    <a href="${pageContext.request.contextPath}/members/new" class="btn">+ Add Member</a>
    <a href="${pageContext.request.contextPath}/logout" class="btn">Logout</a>
  </div>
  <h2>Members</h2>
  <table>
    <thead>
      <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="m" items="${members}">
        <tr>
          <td>${m.id}</td>
          <td>${m.fullName}</td>
          <td>${m.email}</td>
          <td>${m.phone}</td>
          <td>
            <a class="btn" href="${pageContext.request.contextPath}/members/${m.id}/edit">Edit</a>
            <form style="display:inline" method="post" action="${pageContext.request.contextPath}/members/${m.id}/delete">
              <button class="btn" type="submit" onclick="return confirm('Delete?')">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
