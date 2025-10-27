package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.repositry.BookRepositry;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepositry bookRepositry; // Mock repository

    @InjectMocks
    private BookServiceImpl bookService; // Service with mock injected

    private Book book1;

    @BeforeEach
    void setUp() {
//        book1 = new Book(1, "Java Basics", "John Doe", BookStatus.AVAILABLE);
        book1 = new Book(1, "Java Basics", "John Doe", "tejas", 123.3,BookStatus.AVAILABLE);
    }

    @Test
    void testGetAllBooks_Test() {
        when(bookRepositry.findAll()).thenReturn(List.of(book1));

        List<Book> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Java Basics", books.get(0).getTitle());
        assertEquals("John Doe", books.get(0).getAuthor());
        verify(bookRepositry, times(1)).findAll();
    }

    @Test
    void testGetBookById_found_Test() {
        when(bookRepositry.findById(1)).thenReturn(Optional.of(book1));

        Optional<Book> result = bookService.getBookById(1);

        assertTrue(result.isPresent());
        assertEquals("Java Basics", result.get().getTitle());
        verify(bookRepositry, times(1)).findById(1);
    }

    @Test
    void testGetBookById_notFound_Test() {
        when(bookRepositry.findById(2)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.getBookById(2);

        assertFalse(result.isPresent());
        verify(bookRepositry, times(1)).findById(2);
    }

    @Test
    void testCreateBook_Test() {
        when(bookRepositry.save(book1)).thenReturn(book1);

        Book result = bookService.createBook(book1);

        assertEquals("Java Basics", result.getTitle());
        verify(bookRepositry, times(1)).save(book1);
    }

    @Test
    void testUpdateBook_found_Test() {
        Book updatedBook = new Book(null, "Advanced Java", "John Doe", "Tejas", 123.3, BookStatus.AVAILABLE);

        when(bookRepositry.findById(1)).thenReturn(Optional.of(book1));
        when(bookRepositry.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.updateBook(1, updatedBook);

        assertEquals("Advanced Java", result.getTitle());
        assertEquals("John Doe", result.getAuthor());
        verify(bookRepositry, times(1)).findById(1);
        verify(bookRepositry, times(1)).save(book1);
    }



    @Test
    void testUpdateBook_notFound_Test() {
        Book updatedBook = new Book(null, "Advanced Java", "John Doe", "jay",11.2, BookStatus.AVAILABLE);

        when(bookRepositry.findById(2)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                bookService.updateBook(2, updatedBook)
        );

        assertEquals("not find Book record with id2", exception.getMessage());
        verify(bookRepositry, times(1)).findById(2);
        verify(bookRepositry, never()).save(any(Book.class));
    }

    @Test
    void testDeleteBook_found_Test() {
        when(bookRepositry.findById(1)).thenReturn(Optional.of(book1));

        bookService.deleteBook(1);

        verify(bookRepositry, times(1)).delete(book1);
    }

    @Test
    void testDeleteBook_notFound_Test() {
        when(bookRepositry.findById(2)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                bookService.deleteBook(2)
        );

        assertEquals("Book not found with id2", exception.getMessage());
        verify(bookRepositry, never()).delete(any(Book.class));
    }
}
