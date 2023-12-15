package com.shop;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @Column(name = "book_id")
    private String id;

    private String name;

    private String author;
    public BookEntity(String id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public BookEntity() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }
}
