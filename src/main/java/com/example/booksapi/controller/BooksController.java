package com.example.booksapi.controller;

import com.example.booksapi.model.Book;
import com.example.booksapi.dao.BooksDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksDao booksDao;

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        booksDao.addBook(book);
        return ResponseEntity.ok("Book added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = booksDao.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = booksDao.getBooksByAuthor(author);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        List<Book> books = booksDao.getBooksByCategory(category);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<Book>> getBooksByLanguage(@PathVariable String language) {
        List<Book> books = booksDao.getBooksByLanguage(language);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = booksDao.searchBooks(query);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }
}