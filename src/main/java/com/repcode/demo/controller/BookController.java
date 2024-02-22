package com.repcode.demo.controller;

import com.repcode.demo.dao.BookDao;
import com.repcode.demo.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookDao.findAll();
    }

    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable Long id) {
        return bookDao.findById(id).orElse(null);
    }

    @PostMapping
    public BookEntity addBook(@RequestBody BookEntity book) {
        return bookDao.save(book);
    }

    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity bookDetails) {
        BookEntity book = bookDao.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            return bookDao.save(book);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookDao.deleteById(id);
    }
}

