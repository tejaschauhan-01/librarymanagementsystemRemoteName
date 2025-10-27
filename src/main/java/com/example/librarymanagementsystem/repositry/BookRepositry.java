package com.example.librarymanagementsystem.repositry;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BookRepositry extends JpaRepository<Book, Integer>{
    Page<Book> findByAuthorContaining(String author, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    Set<Book> findByStatus(BookStatus status);

    @Query("SELECT b FROM Book b WHERE " +
            "(:id IS NULL OR b.id = :id) AND " +
            "(:title IS NULL OR b.title LIKE %:title%) AND " +
            "(:author IS NULL OR b.author LIKE %:author%)")
    List<Book> searchBooks(@Param("id") Long id,
                           @Param("title") String title,
                           @Param("author") String author);

}
