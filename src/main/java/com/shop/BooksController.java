package com.shop;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    private BookService bookService;

    BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<Books> books() {
        return ResponseEntity.ok(new Books(bookService.allBooks()));
    }

    @PostMapping(value = "/books", produces = "application/json")
    public ResponseEntity<ResponseBook> add(@RequestBody Book book) {
        ResponseBook responseBook = bookService.addBooks(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", responseBook.getBookEntity().getId());
        return new ResponseEntity<>(responseBook, headers, HttpStatus.CREATED);
    }
}
