<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LIBIS - Member Login</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Lora:wght@400;600&family=Courier+Prime&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
<div class="card-wrapper">
    <div class="index-card">
        <h1>LIBIS</h1>
        <p>Sign in to view the book catalogue</p>

        <c:if test="${not empty error}">
            <p class="error-message"><c:out value="${error}"/></p>
        </c:if>
        <c:if test="${not empty info}">
            <p class="info-message"><c:out value="${info}"/></p>
        </c:if>

        <form action="<c:url value='/login'/>" method="post">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<c:out value='${email}'/>">

            <label for="password">Password</label>
            <input type="password" id="password" name="password">

            <button type="submit">Log In</button>
        </form>
    </div>
</div>
</body>
</html>
