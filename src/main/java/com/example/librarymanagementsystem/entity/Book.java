package com.example.librarymanagementsystem.entity;

import com.example.librarymanagementsystem.utils.BookStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Publisher is required")
    private String publisher;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Book(){}

    public Book(Integer id, String title, String author, String publisher, Double price, BookStatus status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.publisher=publisher;
        this.price=price;
    }
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public BookStatus  getStatus() { return status; }
    public void setStatus(BookStatus status) { this.status = status; }
}
