package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface BookSearchService {
    Page<Book> searchBooksByAuthor(String author, int page, int size);
    Page<Book> searchBookByTitle(String title,int page, int size);
    Set<Book> searchBookByStatus(BookStatus status);
    List<Book> searchBooksByPriceRange(Double minPrice,Double maxPrice);
}
