# Books API

This is a simple RESTful API for managing a collection of books. The API allows users to add books, retrieve them by various criteria, and search through the collection.

## Project Structure

```
books-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── booksapi
│   │   │               ├── BooksApiApplication.java
│   │   │               ├── controller
│   │   │               │   └── BooksController.java
│   │   │               ├── model
│   │   │               │   └── Book.java
│   │   │               └── dao
│   │   │                   └── BooksDao.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── booksapi
│                       └── BooksApiApplicationTests.java
├── pom.xml
└── README.md
```

## Features

- **Add Book**: Add a new book to the collection.
- **Get All Books**: Retrieve a list of all books.
- **Get Books by Author**: Retrieve books written by a specific author.
- **Get Books by Category**: Retrieve books belonging to a specific category.
- **Get Books by Language**: Retrieve books available in a specific language.
- **Search Books**: Search for books based on a query string.

## Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd books-api
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. The API will be available at `http://localhost:8080`.

## Usage Examples

- **Add a Book**:
  ```
  POST /api/books
  {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "language": "English",
      "category": "Fiction",
      "publishYear": 1925
  }
  ```

- **Get All Books**:
  ```
  GET /api/books
  ```

- **Get Books by Author**:
  ```
  GET /api/books?author=F. Scott Fitzgerald
  ```

- **Get Books by Category**:
  ```
  GET /api/books?category=Fiction
  ```

- **Get Books by Language**:
  ```
  GET /api/books?language=English
  ```

- **Search Books**:
  ```
  GET /api/books/search?query=Gatsby
  ```

## Dependencies

This project uses the following dependencies:

- Spring Boot Starter Web
- JUnit for testing

## License

This project is licensed under the MIT License.