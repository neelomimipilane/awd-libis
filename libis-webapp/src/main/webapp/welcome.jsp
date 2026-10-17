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
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 2rem;
            color: #22333b;
        }
        h1 {
            margin-bottom: 0.25rem;
        }
        p.subtitle {
            color: #6b7280;
            margin-top: 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #ffffff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            margin-top: 1.5rem;
        }
        th, td {
            padding: 0.6rem 0.9rem;
            text-align: left;
            border-bottom: 1px solid #e5e7eb;
        }
        th {
            background-color: #22333b;
            color: #ffffff;
        }
        .available {
            color: #15803d;
            font-weight: bold;
        }
        .unavailable {
            color: #b91c1c;
            font-weight: bold;
        }
        a.action-link {
            margin-right: 0.75rem;
            color: #22333b;
            text-decoration: none;
            font-size: 0.85rem;
        }
        a.action-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Welcome, ${sessionScope.member.fullName}</h1>
    <p class="subtitle">Here is the full LIBIS book catalogue.</p>

    <table>
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
                                <span class="available">Available</span>
                            </c:when>
                            <c:otherwise>
                                <span class="unavailable">Checked out</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="action-link" href="#">Search</a>
                        <a class="action-link" href="#">Borrow</a>
                        <a class="action-link" href="#">Reserve</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
