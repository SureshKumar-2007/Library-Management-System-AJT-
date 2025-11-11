<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Books</title>
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
    <a href="${pageContext.request.contextPath}/books/new" class="btn">+ Add Book</a>
    <a href="${pageContext.request.contextPath}/logout" class="btn">Logout</a>
  </div>
  <h2>Books</h2>
  <table>
    <thead>
      <tr>
        <th>ID</th><th>ISBN</th><th>Title</th><th>Author</th><th>Category</th><th>Total</th><th>Available</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="b" items="${books}">
        <tr>
          <td>${b.id}</td>
          <td>${b.isbn}</td>
          <td>${b.title}</td>
          <td>${b.author}</td>
          <td>${b.category}</td>
          <td>${b.totalCopies}</td>
          <td>${b.availableCopies}</td>
          <td>
            <a class="btn" href="${pageContext.request.contextPath}/books/${b.id}/edit">Edit</a>
            <form style="display:inline" method="post" action="${pageContext.request.contextPath}/books/${b.id}/delete">
              <button class="btn" type="submit" onclick="return confirm('Delete?')">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
