package com.libis.servlet;

import com.libis.model.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        @SuppressWarnings("unchecked")
        List<Member> members = (List<Member>) getServletContext().getAttribute("members");

        Member matchedMember = null;
        if (email != null && password != null) {
            for (Member member : members) {
                if (member.getEmail().equalsIgnoreCase(email.trim())
                        && member.getPassword().equals(password)) {
                    matchedMember = member;
                    break;
                }
            }
        }

        if (matchedMember != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", matchedMember);
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        } else {
            request.setAttribute("error", "Incorrect email or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}