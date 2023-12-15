package com.shop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

public class BookServiceTest {


    @Test
    void shouldGetBooksFromDataBase() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(new BookEntity("1", "java", "James Gosling")));

        BookService bookService = new BookService(bookRepository);
        Book expectedBook = new Book("java", "James Gosling");
        Assertions.assertEquals(List.of(expectedBook), bookService.allBooks());
    }

    @Test
    public void shouldAddBookToDatabase() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        String uuid = UUID.randomUUID().toString();
        BookEntity bookEntity = new BookEntity(uuid, "java", "James Gosling");

        ResponseBook expectedResponse = new ResponseBook(new BookEntity(uuid, "java", "James Gosling"));
        Mockito.when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);

        Book book = new Book("java", "James Gosling");
        ResponseBook actualResponse = bookService.addBooks(book);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
