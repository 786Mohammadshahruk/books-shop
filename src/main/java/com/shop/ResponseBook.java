package com.shop;

import java.util.Objects;

public class ResponseBook {
    private BookEntity bookEntity;

    public ResponseBook(BookEntity bookEntity) {
       this.bookEntity = bookEntity;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseBook that = (ResponseBook) o;
        return Objects.equals(bookEntity, that.bookEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookEntity);
    }
}
