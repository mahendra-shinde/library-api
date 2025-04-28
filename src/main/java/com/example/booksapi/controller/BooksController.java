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

    /**
     * Adds a new book to the database.
     *
     * @param book The book to be added.
     * @return A response indicating the success of the operation.
     */
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        booksDao.addBook(book);
        return ResponseEntity.ok("Book added successfully");
    }

    /**
     * Retrieves all books from the database.
     *
     * @return A list of all books or a 404 status if no books are found.
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = booksDao.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    /**
     * Retrieves books by a specific author.
     *
     * @param author The author's name.
     * @return A list of books by the specified author or a 404 status if no books are found.
     */
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = booksDao.getBooksByAuthor(author);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    /**
     * Retrieves books by a specific category.
     *
     * @param category The category of the books.
     * @return A list of books in the specified category or a 404 status if no books are found.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        List<Book> books = booksDao.getBooksByCategory(category);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    /**
     * Retrieves books by a specific language.
     *
     * @param language The language of the books.
     * @return A list of books in the specified language or a 404 status if no books are found.
     */
    @GetMapping("/language/{language}")
    public ResponseEntity<List<Book>> getBooksByLanguage(@PathVariable String language) {
        List<Book> books = booksDao.getBooksByLanguage(language);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }

    /**
     * Searches for books based on a query string.
     *
     * @param query The search query.
     * @return A list of books matching the query or a 404 status if no books are found.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = booksDao.searchBooks(query);
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(books);
    }
}