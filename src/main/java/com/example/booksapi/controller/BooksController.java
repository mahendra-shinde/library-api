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
    public List<Book> getAllBooks() {
        return booksDao.getAllBooks();
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return booksDao.getBooksByAuthor(author);
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
    public List<Book> getBooksByLanguage(@PathVariable String language) {
        return booksDao.getBooksByLanguage(language);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String query) {
        return booksDao.searchBooks(query);
    }
}