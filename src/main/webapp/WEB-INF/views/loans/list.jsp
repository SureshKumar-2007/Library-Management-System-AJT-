<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Loans</title>
        <style>
            body {
                font-family: Arial;
                padding:20px;
            }
            .btn {
                padding:6px 10px;
                border:1px solid #333;
                text-decoration:none;
                display:inline-block;
                background:#fff;
                cursor:pointer;
            }
            table {
                width:100%;
                border-collapse:collapse;
                margin-top:12px;
            }
            th,td {
                border:1px solid #ccc;
                padding:8px;
            }
            th {
                background:#eee;
            }
        </style>
    </head>
    <body>

        <div>
            <a class="btn" href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
            <a class="btn" href="${pageContext.request.contextPath}/books">Books</a>
            <a class="btn" href="${pageContext.request.contextPath}/members">Members</a>
            <a class="btn" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>

        <h2>Issue Book</h2>
        <form method="post" action="${pageContext.request.contextPath}/loans/issue">
            Book:
            <select name="bookId">
                <c:forEach var="b" items="${books}">
                    <option value="${b.id}">${b.title} (Avail: ${b.availableCopies})</option>
                </c:forEach>
            </select>
            Member:
            <select name="memberId">
                <c:forEach var="m" items="${members}">
                    <option value="${m.id}">${m.fullName}</option>
                </c:forEach>
            </select>
            <button class="btn" type="submit">Issue</button>
        </form>

        <h2>Open Loans</h2>
        <table>
            <tr><th>ID</th><th>Book ID</th><th>Member ID</th><th>Issue</th><th>Due</th><th>Action</th></tr>
                    <c:forEach var="l" items="${openLoans}">
                <tr>
                    <td>${l.id}</td>
                    <td>${l.bookId}</td>
                    <td>${l.memberId}</td>
                    <td>${l.issueDate}</td>
                    <td>${l.dueDate}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/loans/${l.id}/return">
                            <button class="btn" type="submit">Return</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>Loan History</h2>
        <table>
            <tr><th>ID</th><th>Book ID</th><th>Member ID</th><th>Issue</th><th>Due</th><th>Returned</th><th>Fine</th></tr>
                    <c:forEach var="l" items="${allLoans}">
                <tr>
                    <td>${l.id}</td>
                    <td>${l.bookId}</td>
                    <td>${l.memberId}</td>
                    <td>${l.issueDate}</td>
                    <td>${l.dueDate}</td>
                    <td>${l.returnDate}</td>
                    <td>${l.fineAmount}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
