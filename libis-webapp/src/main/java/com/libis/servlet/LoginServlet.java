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
            // Success: carry identity forward via the session, then redirect
            // (redirect-after-post avoids a resubmission if the user refreshes welcome.jsp).
            HttpSession session = request.getSession();
            session.setAttribute("member", matchedMember);
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        } else {
            // Failure: forward back to login.jsp with a request-scoped error message.
            request.setAttribute("error", "Invalid email or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If someone navigates here directly with GET, just send them to the login form.
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
