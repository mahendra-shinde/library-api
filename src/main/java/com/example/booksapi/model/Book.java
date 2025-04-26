package com.example.booksapi.model;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private String language;

    public Book() {
    }

    public Book(int id, String title, String author, String category, String language) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}