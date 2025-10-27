# librarymanagementsystem

Library Management System (Spring Boot)

Library Management System built using Spring Boot, designed to manage books, authors, and users efficiently.
It supports CRUD operations, pagination, validation, exception handling.

## Features

- ✅ Add, update, delete, and fetch books
- ✅ Search books by title, author, or status
- ✅ Pagination using Pageable
- ✅ Input validation using @Valid and annotations like @NotNull, @Size, etc.
- ✅ Logging using SLF4J/Logback
- ✅ Unit testing with JUnit and Mockito




## Tech Stack

- Backend -> Spring Boot (Web, JPA, Validation, Security)
- Database -> PostgreSQL / MySQL / H2 (for testing)
- ORM -> Hibernate
- Build Tool -> Maven / Gradle
- Testing -> JUnit 5, Mockito
- Logging -> SLF4J + Logback



## REST Endpoints
### -> Method   -   Endpoint    -   Description
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/books` | Fetch all books |
| GET | `/books/{id}` | Get a book by ID |
| POST | `/books` | Add a new book |
| PUT | `/books/{id}` | Update existing book |
| DELETE | `/books/{id}` | Delete a book |
| GET | `/BookSearchBy/title` | Search books by title |
| GET | `/BookSearchBy/author` | Search books by author |
| GET | `/BookSearchBy/status/{status}` | Search by status (`AVAILABLE`, `LOST`, `ISSUED`, `RESERVED`) |



### ddl-auto

- create -	Drops existing tables and creates new ones from your entities. Existing data is lost.
- create-drop -	Same as create, but also drops tables when app stops.
- update -	Tries to update existing tables to match your entities. Adds new columns, keeps existing data.
- validate -	Checks that the tables match your entities. Throws error if mismatch.
- none -	Does nothing to the database structure.