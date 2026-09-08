<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.member}">
    <c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LIBIS - Book Catalogue</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Lora:wght@400;600&family=Courier+Prime&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="page">
    <h1>Welcome, ${sessionScope.member.fullName}</h1>
    <p class="subtitle">Here is the full LIBIS book catalogue.</p>

    <table class="ledger">
        <thead>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Availability</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${applicationScope.books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.genre}</td>
                <td>
                    <c:choose>
                        <c:when test="${book.available}">
                            <span class="badge badge-available">Available</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-out">Checked out</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="actions">
                    <a href="#">Search</a>
                    <a href="#">Borrow</a>
                    <a href="#">Reserve</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
