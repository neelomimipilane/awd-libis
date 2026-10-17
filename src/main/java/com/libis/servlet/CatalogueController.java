
package com.libis.servlet;

import com.libis.model.Book;
import com.libis.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "CatalogueController", urlPatterns = "/catalogue")
public class CatalogueController extends HttpServlet {

    private static final String WELCOME_VIEW =
            "/WEB-INF/views/welcome.jsp";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether the user is logged in.
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        User user = (User) session.getAttribute("user");

        // Retrieve books loaded by your application.
        @SuppressWarnings("unchecked")
        List<Book> allBooks =
                (List<Book>) getServletContext().getAttribute("books");

        // Prevent a NullPointerException if the book list was not initialized.
        if (allBooks == null) {
            allBooks = new ArrayList<>();
        }

        // Read the search term.
        String term = request.getParameter("q");
        term = (term == null) ? "" : term.trim();

        String needle = term.toLowerCase(Locale.ROOT);

        // Filter books by title.
        List<Book> results = new ArrayList<>();

        for (Book book : allBooks) {
            if (book != null
                    && book.getTitle() != null
                    && (needle.isEmpty()
                    || book.getTitle().toLowerCase(Locale.ROOT)
                    .contains(needle))) {

                results.add(book);
            }
        }

        // Pass information to the JSP.
        request.setAttribute("memberName", user.getFullName());
        request.setAttribute("books", results);
        request.setAttribute("resultCount", results.size());
        request.setAttribute("totalBooks", allBooks.size());
        request.setAttribute("searchTerm", term);

        request.getRequestDispatcher(WELCOME_VIEW)
                .forward(request, response);
    }
}
