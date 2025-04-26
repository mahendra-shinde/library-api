package com.example.booksapi.dao;

import org.springframework.stereotype.Repository;

import com.example.booksapi.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BooksDao {
    private final List<Book> books = new ArrayList<>();

    public BooksDao() {
        // Prepopulate the books list with sample data
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "English"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", "English"));
        books.add(new Book(3, "1984", "George Orwell", "Dystopian", "English"));
        books.add(new Book(4, "Pride and Prejudice", "Jane Austen", "Romance", "English"));
        books.add(new Book(5, "The Alchemist", "Paulo Coelho", "Adventure", "Portuguese"));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByLanguage(String language) {
        return books.stream()
                .filter(book -> book.getLanguage().equalsIgnoreCase(language))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}