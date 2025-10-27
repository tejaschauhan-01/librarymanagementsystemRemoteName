package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.services.BookSearchServiceImpl;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookSearchControllerTest {

    @Mock
    private BookSearchServiceImpl bookSearchService;

    @InjectMocks
    private BookSearchController bookSearchController;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book1 = new Book();
        book1.setId(1);
        book1.setTitle("Java Programming");
        book1.setAuthor("James Gosling");
//        book1.setStatus(BookStatus.AVAILABLE);

        book2 = new Book();
        book2.setId(2);
        book2.setTitle("Spring Boot Guide");
        book2.setAuthor("Craig Walls");

    }

    // Test for searchByAuthor
    @Test
    void testSearchByAuthorTest() {
        List<Book> books = Arrays.asList(book1, book2);
        Page<Book> mockPage = new PageImpl<>(books, PageRequest.of(0, 10), books.size());

        when(bookSearchService.searchBooksByAuthor("James", 0, 10)).thenReturn(mockPage);

        ResponseEntity<?> result = bookSearchController.searchByAuthor("James", 0, 10);
        String statuscode= result.getStatusCode().toString();
        assertEquals("200 OK", statuscode);
        assertEquals(true,result.hasBody());
        verify(bookSearchService, times(1)).searchBooksByAuthor("James", 0, 10);
    }

    // Test for searchByTitle
    @Test
    void testSearchByTitleTest() {
        List<Book> books = Collections.singletonList(book1);
        Page<Book> mockPage = new PageImpl<>(books, PageRequest.of(0, 10), books.size());

        when(bookSearchService.searchBookByTitle("Java", 0, 10)).thenReturn(mockPage);

        Page<Book> result = bookSearchController.searchByTitle("Java", 0, 10);

        assertEquals(1, result.getContent().size());
        assertEquals("Java Programming", result.getContent().get(0).getTitle());
        verify(bookSearchService, times(1)).searchBookByTitle("Java", 0, 10);
    }

    // Test for searchByStatus
    @Test
    void testSearchByStatusTest() {
        Set<Book> books = new HashSet<>(Arrays.asList(book1, book2));
        when(bookSearchService.searchBookByStatus(BookStatus.AVAILABLE)).thenReturn(books);

        Set<Book> result = bookSearchController.searchByStatus(BookStatus.AVAILABLE);
        result.forEach(book -> System.out.println(book));

        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());
        verify(bookSearchService, times(1)).searchBookByStatus(BookStatus.AVAILABLE);
    }

    // Test for searchBooks (Global search)
    @Test
    void testSearchBooksTest() {
        List<Book> books = Arrays.asList(book1);
        when(bookSearchService.searchBooks(1L, "Java", "James")).thenReturn(books);

        List<Book> result = bookSearchController.searchBooks(1L, "Java", "James");

        assertEquals(1, result.size());
        assertEquals("Java Programming", result.get(0).getTitle());
        verify(bookSearchService, times(1)).searchBooks(1L, "Java", "James");
    }
}
