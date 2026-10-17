<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LIBIS - Book Catalogue</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Lora:wght@400;600&family=Courier+Prime&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
<div class="page">
    <div class="topbar">
        <h1>Welcome, <c:out value="${memberName}"/></h1>
        <a class="logout-link" href="<c:url value='/logout'/>">Log out</a>
    </div>

    <p class="subtitle">
        <c:choose>
            <c:when test="${empty searchTerm}">
                Here is the full LIBIS book catalogue (<c:out value="${totalBooks}"/> books).
            </c:when>
            <c:otherwise>
                <c:out value="${resultCount}"/> of <c:out value="${totalBooks}"/> books match
                "<c:out value="${searchTerm}"/>".
            </c:otherwise>
        </c:choose>
    </p>

    <form class="search-bar" action="<c:url value='/catalogue'/>" method="get">
        <input type="text" name="q" placeholder="Search by title" value="<c:out value='${searchTerm}'/>">
        <button type="submit">Search</button>
        <c:if test="${not empty searchTerm}">
            <a class="logout-link" href="<c:url value='/catalogue'/>">Clear</a>
        </c:if>
    </form>

    <c:choose>
        <c:when test="${empty books}">
            <p class="info-message">No books found. Try a different title.</p>
        </c:when>
        <c:otherwise>
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
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td><c:out value="${book.title}"/></td>
                        <td><c:out value="${book.author}"/></td>
                        <td><c:out value="${book.genre}"/></td>
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
                            <a href="#">Borrow</a>
                            <a href="#">Reserve</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
