<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                padding: 20px;
            }
            .btn {
                padding:6px 10px;
                border:1px solid #333;
                text-decoration:none;
                display:inline-block;
                background:#fff;
                cursor:pointer;
                margin-right:8px;
            }
        </style>
    </head>
    <body>
        <div class="nav">
            <a class="btn" href="${pageContext.request.contextPath}/books">Books</a>
            <a class="btn" href="${pageContext.request.contextPath}/members">Members</a>
            <a class="btn" href="${pageContext.request.contextPath}/loans">Loans</a>
            <a class="btn" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>

        <h2>Welcome to Library Management</h2>
        <p>Use the navigation above.</p>
    </body>
</html>
