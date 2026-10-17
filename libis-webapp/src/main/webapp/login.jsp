<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LIBIS - Member Login</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-box {
            background: #ffffff;
            padding: 2rem 2.5rem;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.15);
            width: 320px;
        }
        h1 {
            font-size: 1.4rem;
            margin-bottom: 0.25rem;
            color: #22333b;
        }
        p.subtitle {
            margin-top: 0;
            color: #6b7280;
            font-size: 0.9rem;
        }
        label {
            display: block;
            margin-top: 1rem;
            margin-bottom: 0.25rem;
            font-size: 0.9rem;
            color: #22333b;
        }
        input[type="email"], input[type="password"] {
            width: 100%;
            padding: 0.5rem;
            box-sizing: border-box;
            border: 1px solid #cbd2d9;
            border-radius: 4px;
        }
        .error {
            background-color: #fdecea;
            color: #b91c1c;
            padding: 0.6rem 0.8rem;
            border-radius: 4px;
            font-size: 0.85rem;
            margin-top: 1rem;
        }
        button {
            margin-top: 1.5rem;
            width: 100%;
            padding: 0.6rem;
            background-color: #22333b;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
        }
        button:hover {
            background-color: #0e1a1f;
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h1>LIBIS</h1>
        <p class="subtitle">Sign in to view the book catalogue</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="LoginServlet" method="post">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Log In</button>
        </form>
    </div>
</body>
</html>
