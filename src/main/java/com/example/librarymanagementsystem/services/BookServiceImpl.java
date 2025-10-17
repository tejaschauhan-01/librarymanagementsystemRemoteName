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
        logger.info("return list of books from service to controller");
        return  bookRepositry.findAll();
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        logger.info("successfully return book detail found by book id");
        return bookRepositry.findById(id);
    }

    @Override
    public Book createBook(Book book) {
        logger.info("book has been created at service layer and return to controller");
        return  bookRepositry.save(book);
    }

    @Override
    public Book updateBook(Integer id, Book updatedbook) {
        logger.info("book details now has been updated and return to controller ");
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
