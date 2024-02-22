package com.repcode.demo.controller;

import com.repcode.demo.dao.BookDao;
import com.repcode.demo.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookControllerTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookDao bookDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBooksTest() {
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity(1L, "Book 1", "Author 1", 20.0));
        bookList.add(new BookEntity(2L, "Book 2", "Author 2", 25.0));

        when(bookDao.findAll()).thenReturn(bookList);

        List<BookEntity> result = bookController.getAllBooks();

        assertEquals(2, result.size());
    }

    @Test
    void getBookByIdTest() {
        BookEntity book = new BookEntity(1L, "Book 1", "Author 1", 20.0);

        when(bookDao.findById(1L)).thenReturn(Optional.of(book));

        BookEntity result = bookController.getBookById(1L);

        assertEquals("Book 1", result.getTitle());
    }

    @Test
    void addBookTest() {
        BookEntity book = new BookEntity(1L, "Book 1", "Author 1", 20.0);

        when(bookDao.save(book)).thenReturn(book);

        BookEntity result = bookController.addBook(book);

        assertEquals("Book 1", result.getTitle());
    }

    @Test
    void updateBookTest() {
        BookEntity existingBook = new BookEntity(1L, "Existing Book", "Existing Author", 30.0);
        BookEntity updatedBookDetails = new BookEntity(1L, "Updated Book", "Updated Author", 35.0);

        when(bookDao.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookDao.save(existingBook)).thenReturn(existingBook);

        BookEntity result = bookController.updateBook(1L, updatedBookDetails);

        assertEquals("Updated Book", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        assertEquals(35.0, result.getPrice());
    }

    @Test
    void deleteBookTest() {
        doNothing().when(bookDao).deleteById(1L);

        bookController.deleteBook(1L);

        verify(bookDao, times(1)).deleteById(1L);
    }
}
