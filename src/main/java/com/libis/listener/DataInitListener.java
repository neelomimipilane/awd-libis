package com.libis.listener;

import com.libis.model.Book;
import com.libis.model.Member;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class DataInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("members", buildMembers());
        sce.getServletContext().setAttribute("books", buildBooks());
    }

    private List<Member> buildMembers() {
        List<Member> members = new ArrayList<>();
        members.add(new Member(1, "Neelo", "Pilane", "neelopilane@gmail.com", "password1"));
        members.add(new Member(2, "Tshiamo", "Frank", "tshiamofrank@gmail.com", "password2"));
        members.add(new Member(3, "Bianca", "Kedukaetswe", "biancak@gmail.com", "password3"));
        members.add(new Member(4, "Lucky", "Chinaye", "luckyc@gmail.com", "password4"));
        members.add(new Member(5, "Bame", "Mosimakoko", "bamem@gmail.com", "password5"));
        return members;
    }

    private List<Book> buildBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Introduction to Jakarta EE", "Sylvester Mokgwe", "Computer Engineering",
                "A comprehensive guide to Jakarta EE libraries.", false));
        books.add(new Book(2, "Basics of Clean Code", "Africa Code Academy", "Software Engineering",
                "A handbook of agile software craftsmanship.", true));
        books.add(new Book(3, "Things Fall Apart", "Chinua Achebe", "Fiction",
                "A classic novel of pre-colonial life in Nigeria.", true));
        books.add(new Book(4, "The Hate U Give", "Angie Thomas", "Contemporary fiction and social justice literature",
                "The Hate U Give follows a 16-year-old girl named Starr Carter, who is drawn to activism after she witnesses a police officer shoot and kill her unarmed childhood best friend, Khalil.", true));
        books.add(new Book(5, "Computer Related Mathematics and Statistics", "Rachna Verma", "Computer Math",
                "Introduction to Computer Math and Statistics", true));
        books.add(new Book(6, "Systems Development", "Botlhe Lucas", "Computer Science",
                "Introduction to how systems work, flow and how they interact with each other", true));
        books.add(new Book(7, "Long Walk to Freedom", "Nelson Mandela", "Biography",
                "The autobiography of the former South African president.", true));
        books.add(new Book(8, "Database System Concepts", "Karabo Mokotedi", "Computer Science",
                "Foundational concepts of database design and management.", false));
        books.add(new Book(9, "The Polygamist", "Sue Nyathi", "Fiction",
                "The Polygamist is the 2012 debut novel by Zimbabwean author Sue Nyathi. The story follows four women whose lives collide and descend into chaos due to their relationships with Jonasi Gomora, a wealthy and powerful banking magnate who secretly juggles multiple parallel lives", false));
        books.add(new Book(10, "The Pearl", "John Steinbeck", "Fiction",
                "A short novel about greed and human nature.", true));
        return books;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}