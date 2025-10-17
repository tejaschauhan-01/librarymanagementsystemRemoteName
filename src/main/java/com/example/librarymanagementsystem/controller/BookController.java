package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.repositry.BookRepositry;
import com.example.librarymanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        logger.info("executed get request to get books list from database");
        return bookService.getAllBooks();
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        logger.info("executed get request to get book by id -> {}", id);
        return bookService.getBookById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        logger.info("executed post request to create book" );
        return bookService.createBook(book);
    }

    // Update book (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
       try{
           logger.info("execution put request to updated book details");
           Book book= bookService.updateBook(id,updatedBook);
           return ResponseEntity.ok(book);
       }
       catch (Exception e){
           logger.error("Exeption occures "+e);
        return ResponseEntity.notFound().build();
       }

    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Integer id) {
        try {
            logger.info("performing deletion on book with id "+id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Exeption occures in delete request "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

