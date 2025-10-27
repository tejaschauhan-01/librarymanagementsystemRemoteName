package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.repositry.BookRepositry;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookSearchServiceImplTest {

    @Mock
    private BookRepositry bookRepositry;

    @InjectMocks
    private BookSearchServiceImpl bookSearchService;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book1 = new Book();
        book1.setId(1);
        book1.setTitle("Java Basics");
        book1.setAuthor("James Gosling");
        book1.setStatus(BookStatus.AVAILABLE);

        book2 = new Book();
        book2.setId(2);
        book2.setTitle("Spring Boot in Action");
        book2.setAuthor("Craig Walls");
        book2.setStatus(BookStatus.ISSUED);
    }

    @Test
    void testSearchBooksByAuthorTest() {
        List<Book> books = Arrays.asList(book1, book2);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("author").ascending());
        Page<Book> mockPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepositry.findByAuthorContaining("James", pageable)).thenReturn(mockPage);

        Page<Book> result = bookSearchService.searchBooksByAuthor("James", 0, 10);

        assertEquals(2, result.getContent().size());
        assertEquals("Java Basics", result.getContent().get(0).getTitle());
        verify(bookRepositry, times(1)).findByAuthorContaining("James", pageable);
    }

    @Test
    void testSearchBookByTitleTest() {
        List<Book> books = Collections.singletonList(book2);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("title").descending());
        Page<Book> mockPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepositry.findByTitleContaining("Spring", pageable)).thenReturn(mockPage);

        Page<Book> result = bookSearchService.searchBookByTitle("Spring", 0, 10);

        assertEquals(1, result.getContent().size());
        assertEquals("Spring Boot in Action", result.getContent().get(0).getTitle());
        verify(bookRepositry, times(1)).findByTitleContaining("Spring", pageable);
    }

    @Test
    void testSearchBookByStatusTest() {
        Set<Book> books = new HashSet<>(Arrays.asList(book1));
        when(bookRepositry.findByStatus(BookStatus.AVAILABLE)).thenReturn(books);

        Set<Book> result = bookSearchService.searchBookByStatus(BookStatus.AVAILABLE);

        assertEquals(1, result.size());
        assertTrue(result.contains(book1));
        verify(bookRepositry, times(1)).findByStatus(BookStatus.AVAILABLE);
    }

    @Test
    void testSearchBooksTest() {
        List<Book> books = Arrays.asList(book1);
        when(bookRepositry.searchBooks(1L, "Java", "James")).thenReturn(books);

        List<Book> result = bookSearchService.searchBooks(1L, "Java", "James");

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
        verify(bookRepositry, times(1)).searchBooks(1L, "Java", "James");
    }

    @Test
    void testSearchBooksByPriceRangeEmptyTest() {
        List<Book> result = bookSearchService.searchBooksByPriceRange(100.0, 200.0);
        assertTrue(result.isEmpty());
    }
}
