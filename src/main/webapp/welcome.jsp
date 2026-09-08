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
</head>
<body>
<h1>Welcome, ${sessionScope.member.fullName}</h1>
<p>Here is the full LIBIS book catalogue.</p>

<table border="1" cellpadding="6">
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
                    <c:when test="${book.available}">Available</c:when>
                    <c:otherwise>Checked out</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="#">Search</a>
                <a href="#">Borrow</a>
                <a href="#">Reserve</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
