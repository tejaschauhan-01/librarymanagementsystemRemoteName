package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.services.BookSearchService;
import com.example.librarymanagementsystem.repositry.BookRepositry;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookSearchServiceImpl implements BookSearchService{
    private static final Logger logger = LoggerFactory.getLogger(BookSearchServiceImpl.class);

    @Autowired
          BookRepositry bookRepositry;
    @Override
    public Page<Book> searchBooksByAuthor(String author, int page, int size) {
        if(page<0 || size<0 ){
            throw new IllegalArgumentException("incorrect page or size value");
        }

        logger.info("book search with author "+author+" with page "+page+"and size "+size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("author").ascending());
        logger.info("created pagable reuest: "+pageable);
        Page<Book> books= bookRepositry.findByAuthorContaining(author,pageable);
        logger.info("Books fetch from database are : "+books);
        return books;
    }

    @Override
    public List<Book> searchBooksByPriceRange(Double minPrice, Double maxPrice) {
        return List.of();
    }

    @Override
    public List<Book> searchBooks(Long id, String title, String author) {
        logger.info("book search by id or title or author");
        return  bookRepositry.searchBooks(id,title,author);
    }

    @Override
    public Page<Book> searchBookByTitle(String title, int page, int size){
        logger.info("book search by title : "+title);
        Pageable pageable = PageRequest.of(page,size, Sort.by("title").descending());
        Page<Book> books= bookRepositry.findByTitleContaining(title,pageable);
        logger.info("books are : "+ books);
        return books;
    }

    public Set<Book> searchBookByStatus(BookStatus status){
        logger.info("search book with status");
        Set<Book> books= bookRepositry.findByStatus(status);
        logger.info("books are found by status \n "+books);
        return books;
    }

}
