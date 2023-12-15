package com.shop;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookEntities.stream().map(bookEntity ->
                new Book(bookEntity.getName(), bookEntity.getAuthor())).collect(Collectors.toList());
    }

    public ResponseBook addBooks(Book book) {
        String uuid = UUID.randomUUID().toString();
        BookEntity bookEntity = bookRepository.save(new BookEntity(uuid, book.getName(), book.getAuthor()));
        return new ResponseBook(bookEntity);
    }
}
