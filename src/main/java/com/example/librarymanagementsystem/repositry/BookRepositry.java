package com.example.librarymanagementsystem.repositry;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BookRepositry extends JpaRepository<Book, Integer>{
    Page<Book> findByAuthorContaining(String author, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    Set<Book> findByStatus(BookStatus status);
}
