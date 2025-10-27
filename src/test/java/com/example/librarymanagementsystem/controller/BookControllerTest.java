package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.services.BookService;
import com.example.librarymanagementsystem.utils.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setup() {
        book1 = new Book(1, "Java Guide", "Jane Yosh", "raj", 112.3, BookStatus.AVAILABLE);
        book2 = new Book(2, "Spring Boot Guide", "Jane Doe", "tejas", 123.3, BookStatus.LOST);
    }

    @Test
    void getAllBooks_ShouldReturnBookList_Test() throws Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Java Guide"));
    }

    @Test
    void getBookById_ShouldReturnBook_WhenExistsA_Test() throws Exception {
        Mockito.when(bookService.getBookById(1)).thenReturn(Optional.of(book1));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Guide"));
    }

    @Test
    void getBookById_ShouldReturn404_WhenNotExists_Test() throws Exception {
        Mockito.when(bookService.getBookById(99)).thenReturn(Optional.empty());
        mockMvc.perform(get("/books/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBook_ShouldReturnCreatedBook_Test() throws Exception {
        Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book1);

//        Jane Yosh", "raj", 112.3,
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Java Guide\", \"author\":\"Jane Yosh\", \"publisher\":\"raj\", \"price\":112.3, \"status\":\"AVAILABLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("AVAILABLE"));

    }

    @Test
    void updateBook_ShouldReturnUpdatedBook_Test() throws Exception {
        Mockito.when(bookService.updateBook(eq(2), Mockito.any(Book.class))).thenReturn(book2);
//        Mockito.when(bookService.updateBook(eq(2), Mockito.any(Book.class)))
//                .thenAnswer(invocation -> invocation.getArgument(1)); // return the request object

        mockMvc.perform(put("/books/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Spring Boot Guide\", \"author\":\"Jane Doe\", \"publisher\":\"tejas\",\"price\":123.3, \"status\":\"LOST\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Spring Boot Guide"));
    }

    @Test
    void deleteBook_ShouldReturnNoContent_Test() throws Exception {
        Mockito.doNothing().when(bookService).deleteBook(1);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBook_ShouldReturnNotFound_WhenExceptionThrown_Test() throws Exception {
        Mockito.doThrow(new RuntimeException("Book not found")).when(bookService).deleteBook(99);

        mockMvc.perform(delete("/books/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateBook_Should_ReturnNotFound() throws Exception {
        Mockito.doThrow(new RuntimeException("Book not found for update")).when(bookService).updateBook(Mockito.eq(123), Mockito.any(Book.class));
        mockMvc.perform(put("/books/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Spring Boot Guide\", \"author\":\"Jane Doe\", \"publisher\":\"tejas\",\"price\":123.3, \"status\":\"LOST\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());

        // Verify
        Mockito.verify(bookService).updateBook(Mockito.eq(123), Mockito.any(Book.class));
    }
}
