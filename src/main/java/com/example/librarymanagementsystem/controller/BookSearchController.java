package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.librarymanagementsystem.services.BookSearchServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("BookSearchBy")
public class BookSearchController {
    @Autowired
    BookSearchServiceImpl bookSearchService;

    @GetMapping("/author/search")
    public Page<Book> searchByAuthor(
            @RequestParam String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){
        return bookSearchService.searchBooksByAuthor(author,page,size);
    }
    @GetMapping("/title/search")
    public Page<Book> searchByTitle(
            @RequestParam String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){
        return bookSearchService.searchBookByTitle(author,page,size);
    }

    @GetMapping("search/Status/{status}")
    public Set<Book> searchByStatus(@RequestParam BookStatus status){
        return bookSearchService.searchBookByStatus(status);
    }

}
