package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.librarymanagementsystem.services.BookSearchServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("BookSearchBy")
public class BookSearchController {
    private static final Logger logger = LoggerFactory.getLogger(BookSearchController.class);

    @Autowired
    BookSearchServiceImpl bookSearchService;

    @GetMapping("/author")
    public ResponseEntity<?>searchByAuthor(
            @RequestParam String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){

        try{
            logger.info("books search parameters are : author="+author+"page="+page+"size="+size);
            Page<Book> books = bookSearchService.searchBooksByAuthor(author,page,size);
            return ResponseEntity.ok(books);
        }
        catch (Exception e){
//            throw new IllegalArgumentException("not !good bad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/title")
    public Page<Book> searchByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){
        logger.info("books search parameters are : title="+title+"page="+page+"size="+size);

        return bookSearchService.searchBookByTitle(title,page,size);
    }

    @GetMapping("/Status/{status}")
    public Set<Book> searchByStatus(@RequestParam BookStatus status){
        logger.info("books search with status" + status);
        return bookSearchService.searchBookByStatus(status);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        logger.info("books search parameters are : author="+author+"title="+title+"ID ="+id);
        return bookSearchService.searchBooks(id, title, author);

    }


}
