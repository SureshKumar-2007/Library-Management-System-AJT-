<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Member Form</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                padding: 20px;
            }
            label {
                display:block;
                margin:8px 0;
            }
            a.btn, button.btn {
                padding:6px 10px;
                border:1px solid #333;
                text-decoration:none;
                display:inline-block;
                background:#fff;
                cursor:pointer;
            }
        </style>
    </head>
    <body>

        <h2>
            <c:choose>
                <c:when test="${member.id == null}">Add Member</c:when>
                <c:otherwise>Edit Member</c:otherwise>
            </c:choose>
        </h2>

        <c:choose>
            <c:when test="${member.id == null}">
                <c:url var="formAction" value="/members"/>
            </c:when>
            <c:otherwise>
                <c:url var="formAction" value="/members/${member.id}"/>
            </c:otherwise>
        </c:choose>

        <form method="post" action="${formAction}">
            <label>Full Name: <input type="text" name="fullName" value="${member.fullName}" required></label>
            <label>Email: <input type="email" name="email" value="${member.email}"></label>
            <label>Phone: <input type="text" name="phone" value="${member.phone}"></label>
            <button class="btn" type="submit">Save</button>
            <a class="btn" href="<c:url value='/members'/>">Back</a>
        </form>

    </body>
</html>
