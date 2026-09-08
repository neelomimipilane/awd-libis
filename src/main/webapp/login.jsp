<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LIBIS - Member Login</title>
</head>
<body>
<h1>LIBIS</h1>
<p>Sign in to view the book catalogue</p>

<% if (request.getAttribute("error") != null) { %>
<p style="color:#ff00d5;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="LoginServlet" method="post">
    <label for="email">Email</label><br>
    <input type="email" id="email" name="email" required><br>

    <label for="password">Password</label><br>
    <input type="password" id="password" name="password" required><br>

    <button type="submit">Log In</button>
</form>
</body>
</html>
