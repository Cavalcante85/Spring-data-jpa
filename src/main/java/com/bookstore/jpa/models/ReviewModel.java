package com.bookstore.jpa.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_REVIEW")
public class ReviewModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 36)
    private UUID id;

    @Column(nullable = false)
    private String comment;

    @OneToOne
    @JoinColumn(name = "book_id")
    private BookModel book;


    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReviewModel(){

    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", book=" + book +
                '}';
    }
}
