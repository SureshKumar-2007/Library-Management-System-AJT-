<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Book Form</title>
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
                <c:when test="${book.id == null}">Add Book</c:when>
                <c:otherwise>Edit Book</c:otherwise>
            </c:choose>
        </h2>

        <c:choose>
            <c:when test="${book.id == null}">
                <c:url var="formAction" value="/books"/>
            </c:when>
            <c:otherwise>
                <c:url var="formAction" value="/books/${book.id}"/>
            </c:otherwise>
        </c:choose>

        <form method="post" action="${formAction}">
            <label>ISBN: <input type="text" name="isbn" value="${book.isbn}"></label>
            <label>Title: <input type="text" name="title" value="${book.title}" required></label>
            <label>Author: <input type="text" name="author" value="${book.author}"></label>
            <label>Category: <input type="text" name="category" value="${book.category}"></label>
            <label>Total Copies: <input type="number" name="totalCopies" value="${book.totalCopies > 0 ? book.totalCopies : 1}" min="1"></label>
            <label>Available Copies: <input type="number" name="availableCopies" value="${book.availableCopies > 0 ? book.availableCopies : 1}" min="0"></label>
            <button class="btn" type="submit">Save</button>
            <a class="btn" href="<c:url value='/books'/>">Back</a>
        </form>

    </body>
</html>
