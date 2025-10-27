package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.repositry.BookRepositry;
import com.example.librarymanagementsystem.services.BookService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
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
        logger.info("calling controller getMapping,\nfetching all books records");
       List<Book> booklist= bookService.getAllBooks();
        logger.info("all books list are{}", booklist);
       return  booklist;
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {

        logger.info("getting book detail by ID : -> {}", id);
        return bookService.getBookById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create book
    @PostMapping
    public Book createBook( @Valid @RequestBody Book book) {
        logger.info("book object received: " +book );
        return bookService.createBook(book);
    }

    // Update book (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @Valid @RequestBody Book updatedBook) {
       try{
           logger.info("update book, id is :" +id);
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
            logger.info(" delete book id is: "+id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Exeption occures in delete request "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

//    @Test
//    void updateBook_ShouldReturnNotFound_WhenExceptionThrown() throws Exception {
//        // Arrange — mock the service to throw an exception
//        Mockito.when(bookService.updateBook(Mockito.eq(2), Mockito.any(Book.class)))
//                .thenThrow(new RuntimeException("Book not found"));
//
//        // Act — perform PUT request
//        mockMvc.perform(put("/books/2")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"title\":\"Spring Boot Guide\",\"author\":\"Jane Doe\",\"status\":\"AVAILABLE\"}"))
//                // Assert — expect 404 Not Found
//                .andExpect(status().isNotFound());
//    }

}

