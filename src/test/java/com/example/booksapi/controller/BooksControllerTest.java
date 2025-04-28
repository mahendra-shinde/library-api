package com.example.booksapi.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Test class for BooksController.
 * Contains test cases for various endpoints of the Books API.
 * This class uses RestAssured for testing the REST API endpoints.
 * It includes tests for adding a book, retrieving all books,
 * retrieving books by author, category, language, and searching for books.
 * 
 */
// This annotation is used to specify that the test class should be run with Spring Boot's testing support.
// It allows for loading the application context and provides features like dependency injection.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BooksControllerTest {

    // This annotation is used to inject the local server port into the test class.
    // It allows the test to run on a random port assigned by the Spring Boot application.
    @LocalServerPort
    private int port;

    // Base URL for the API. This is used to construct the full URL for the API endpoints.
    private static final String BASE_URL = "http://localhost";

    /**
     * Tests the addition of a new book using the POST /api/books endpoint.
     * Verifies that the response status is 200 and the response body contains a success message.
     */
    @Test
    public void testAddBook() {
        given()
            .baseUri(BASE_URL)
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"id\": 6, \"title\": \"Test Book\", \"author\": \"Test Author\", \"category\": \"Test Category\", \"language\": \"English\"}")
        .when()
            .post("/api/books")
        .then()
            .statusCode(200)
            .body(equalTo("Book added successfully"));
    }

    /**
     * Tests retrieving all books using the GET /api/books endpoint.
     * Verifies that the response status is 200 and the response contains at least one book.
     */
    @Test
    public void testGetAllBooks() {
        given()
            .baseUri(BASE_URL)
            .port(port)
        .when()
            .get("/api/books")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    /**
     * Tests retrieving books by a specific author using the GET /api/books/author/{author} endpoint.
     * Verifies that the response status is 200 and all returned books have the specified author.
     */
    @Test
    public void testGetBooksByAuthor() {
        given()
            .baseUri(BASE_URL)
            .port(port)
        .when()
            .get("/api/books/author/F. Scott Fitzgerald")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("author", everyItem(equalToIgnoringCase("F. Scott Fitzgerald")));
    }

    /**
     * Tests retrieving books by a specific category using the GET /api/books/category/{category} endpoint.
     * Verifies that the response status is 200 and all returned books belong to the specified category.
     */
    @Test
    public void testGetBooksByCategory() {
        given()
            .baseUri(BASE_URL)
            .port(port)
        .when()
            .get("/api/books/category/Fiction")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("category", everyItem(equalToIgnoringCase("Fiction")));
    }

    /**
     * Tests retrieving books by a specific language using the GET /api/books/language/{language} endpoint.
     * Verifies that the response status is 200 and all returned books are in the specified language.
     */
    @Test
    public void testGetBooksByLanguage() {
        given()
            .baseUri(BASE_URL)
            .port(port)
        .when()
            .get("/api/books/language/English")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("language", everyItem(equalToIgnoringCase("English")));
    }

    /**
     * Tests searching for books using the GET /api/books/search endpoint with a query parameter.
     * Verifies that the response status is 200 and all returned books contain the query string in their title.
     */
    @Test
    public void testSearchBooks() {
        given()
            .baseUri(BASE_URL)
            .port(port)
            .queryParam("query", "Gatsby")
        .when()
            .get("/api/books/search")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("title", everyItem(containsStringIgnoringCase("Gatsby")));
    }
}
