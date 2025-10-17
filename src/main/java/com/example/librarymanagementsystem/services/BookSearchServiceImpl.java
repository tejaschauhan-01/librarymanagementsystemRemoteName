package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.services.BookSearchService;
import com.example.librarymanagementsystem.repositry.BookRepositry;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookSearchServiceImpl implements BookSearchService{
            @Autowired
          BookRepositry bookRepositry;
    @Override
    public Page<Book> searchBooksByAuthor(String author, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("author").ascending());
        return bookRepositry.findByAuthorContaining(author,pageable);
    }

    @Override
    public List<Book> searchBooksByPriceRange(Double minPrice, Double maxPrice) {
        return List.of();
    }
    @Override
    public Page<Book> searchBookByTitle(String title, int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("title").descending());
        return bookRepositry.findByTitleContaining(title,pageable);
    }

    public Set<Book> searchBookByStatus(BookStatus status){
        return bookRepositry.findByStatus(status);
    }

}
