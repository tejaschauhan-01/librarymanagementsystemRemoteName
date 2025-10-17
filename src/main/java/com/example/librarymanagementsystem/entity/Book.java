package com.example.librarymanagementsystem.entity;

import com.example.librarymanagementsystem.utils.BookStatus;
import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String author;
    private String publisher;
    private double price;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

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
