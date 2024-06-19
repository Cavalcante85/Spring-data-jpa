package com.bookstore.jpa.controllers;


import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore/book")
public class BookController {

     private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }




    @GetMapping()
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }



    @PostMapping
    public ResponseEntity<BookModel> saveBook(@Validated @RequestBody BookRecordDto bookRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
    }

}


