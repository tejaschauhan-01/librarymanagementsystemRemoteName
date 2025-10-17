package com.example.librarymanagementsystem.services;
import com.example.librarymanagementsystem.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Integer id);
    Book createBook(Book book);
    Book updateBook(Integer id, Book book);
    void deleteBook(Integer id);
}
