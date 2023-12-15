package com.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
public class BooksControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetNoBooksBackIfTheyAreNotAvailable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books").isEmpty());
    }

    @Test
    void shouldGetBooksIfAvailable() throws Exception {

        Book books = new Book("java", "James Gosling");
        when(bookService.allBooks()).thenReturn(List.of(books));
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("books.[0].name").value("java"))
                .andExpect(jsonPath("books.[0].author").value("James Gosling"));
        verify(bookService, times(1)).allBooks();
    }

    @Test
    void shouldAbleToAddBook() throws Exception {
        Book book = new Book("java", "James Gosling");
        String uuid = UUID.randomUUID().toString();
        ResponseBook responseBook = new ResponseBook(new BookEntity(uuid, "java", "James Gosling"));
        when(bookService.addBooks(book)).thenReturn(responseBook);
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", uuid))
                .andExpect(content().string(objectMapper.writeValueAsString(responseBook)));
         verify(bookService, times(1)).addBooks(book);
    }
}
