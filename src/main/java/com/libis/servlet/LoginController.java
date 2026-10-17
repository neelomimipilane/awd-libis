package com.libis.servlet;

import com.libis.model.User;
import com.libis.data.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private static final String LOGIN_VIEW = "/WEB-INF/views/login.jsp";

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/catalogue");
            return;
        }

        if (request.getParameter("loggedOut") != null) {
            request.setAttribute("info", "You have been logged out.");
        }

        request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        email = (email == null) ? "" : email.trim();
        password = (password == null) ? "" : password;

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error",
                    "Please enter both your email and password.");
            request.setAttribute("email", email);
            request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
            return;
        }

        try {
            User user = userDAO.findByEmail(email);

            if (user == null || !user.getPassword().equals(password)) {
                request.setAttribute("error",
                        "Incorrect email or password. Please try again.");
                request.setAttribute("email", email);
                request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
                return;
            }

            HttpSession existingSession = request.getSession(false);
            if (existingSession != null) {
                request.changeSessionId();
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/catalogue");

        } catch (SQLException e) {
            throw new ServletException("Unable to authenticate user.", e);
        }
    }
}