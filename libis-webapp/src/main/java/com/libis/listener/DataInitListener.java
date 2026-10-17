package com.libis.listener;

import com.libis.model.Book;
import com.libis.model.Member;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Seeds the two in-memory data sets (members and books) exactly once, when the
 * application starts, and publishes them as application-scoped attributes.
 *
 * Design decision: a ServletContextListener + application scope was chosen (over,
 * say, a shared static-field holder class) because it makes the data available
 * identically to both consumers named in the brief without either one having to
 * know about the other:
 *   - LoginServlet reads it via getServletContext().getAttribute("members")
 *   - welcome.jsp reads it via plain EL: ${applicationScope.books}
 * Both are just reading the same ServletContext, so there is exactly one place
 * (this listener) where the data is created.
 */
@WebListener
public class DataInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("members", buildMembers());
        sce.getServletContext().setAttribute("books", buildBooks());
    }

    private List<Member> buildMembers() {
        List<Member> members = new ArrayList<>();
        members.add(new Member(1, "Kagiso", "Molefe", "kagiso.molefe@example.com", "password1"));
        members.add(new Member(2, "Thato", "Dube", "thato.dube@example.com", "password2"));
        members.add(new Member(3, "Boitumelo", "Kgosi", "boitumelo.kgosi@example.com", "password3"));
        members.add(new Member(4, "Lesego", "Phiri", "lesego.phiri@example.com", "password4"));
        members.add(new Member(5, "Naledi", "Sithole", "naledi.sithole@example.com", "password5"));
        return members;
    }

    private List<Book> buildBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Introduction to Algorithms", "Thomas H. Cormen", "Computer Science",
                "A comprehensive guide to algorithms and data structures.", true));
        books.add(new Book(2, "Clean Code", "Robert C. Martin", "Software Engineering",
                "A handbook of agile software craftsmanship.", false));
        books.add(new Book(3, "Things Fall Apart", "Chinua Achebe", "Fiction",
                "A classic novel of pre-colonial life in Nigeria.", true));
        books.add(new Book(4, "A Brief History of Time", "Stephen Hawking", "Science",
                "An exploration of cosmology for the general reader.", true));
        books.add(new Book(5, "The Pragmatic Programmer", "Andrew Hunt", "Software Engineering",
                "Practical tips for becoming a more effective programmer.", false));
        books.add(new Book(6, "Sapiens", "Yuval Noah Harari", "History",
                "A brief history of humankind.", true));
        books.add(new Book(7, "Long Walk to Freedom", "Nelson Mandela", "Biography",
                "The autobiography of the former South African president.", true));
        books.add(new Book(8, "Database System Concepts", "Abraham Silberschatz", "Computer Science",
                "Foundational concepts of database design and management.", false));
        books.add(new Book(9, "Botswana: A History", "Neil Parsons", "History",
                "A historical account of Botswana from earliest times.", true));
        books.add(new Book(10, "The Pearl", "John Steinbeck", "Fiction",
                "A short novel about greed and human nature.", true));
        return books;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // No resources to release for this in-memory assessment.
    }
}
