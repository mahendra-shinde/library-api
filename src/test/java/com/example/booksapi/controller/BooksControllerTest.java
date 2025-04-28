package com.example.booksapi.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BooksControllerTest {
    @LocalServerPort
    private int port;

    private static final String BASE_URL = "http://localhost";

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
