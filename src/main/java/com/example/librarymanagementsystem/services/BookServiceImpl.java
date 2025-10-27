package com.example.librarymanagementsystem.services;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.repositry.BookRepositry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepositry bookRepositry;

    @Override
    public List<Book> getAllBooks(){
        logger.info("fetching data from database and return to controller");
        return bookRepositry.findAll();
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        logger.info("fetch data from database");
        Optional<Book> book= bookRepositry.findById(id);
        logger.info("fetched book is "+book);
        return  book;
    }

    @Override
    public Book createBook(Book book) {
        logger.info("storing book data in database, Book Record : "+book);
        Book savedbook= bookRepositry.save(book);
        logger.info("book created with"+savedbook);
        return savedbook;
    }

    @Override
    public Book updateBook(Integer id, Book updatedbook) {
        logger.info("changeable book id is: "+id+" and updated value object : "+ updatedbook);
        return  bookRepositry.findById(id).map(book->{
            book.setTitle(updatedbook.getTitle());
            book.setAuthor(updatedbook.getAuthor());
            book.setPrice(updatedbook.getPrice());
            book.setPublisher(updatedbook.getPublisher());
            return bookRepositry.save(book);
        }).orElseThrow(()->new RuntimeException("not find Book record with id"+id));
    }


    @Override
    public void deleteBook(Integer id) {
        logger.info("book with id "+id+" has been deleted" );
        bookRepositry.findById(id).ifPresentOrElse(
                bookRepositry::delete,()->{throw new RuntimeException("Book not found with id"+id);}
        );
    }


}
