package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  private final PublisherRepository publisherRepository;


    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }


    public List<BookModel> getAllBooks(){
     return bookRepository.findAll();

    }


    @Transactional
    public BookModel saveBook(BookRecordDto bookRecordDto) {
        try {
            UUID publisherId = bookRecordDto.publisherId(); // Obtendo o ID do editor
            System.out.println("ID do editor: " + publisherId.toString()); // Imprimindo o ID do editor no console

            BookModel book = new BookModel();
            book.setTitle(bookRecordDto.title());
            //book.setPublisher(publisherRepository.findById(publisherId).orElseThrow(() -> new RuntimeException("Editora não encontrada")));
            //book.setPublisher(publisherRepository.getReferenceById(bookRecordDto.publisherId()));
            book.setAuthors(authorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

            // Salvando em cascata (Populando o objeto Review
            ReviewModel reviewModel = new ReviewModel();
            reviewModel.setComment(bookRecordDto.reviewComment());
            reviewModel.setBook(book);
            book.setReview(reviewModel);



            return bookRepository.save(book);
        } catch (Exception e) {
            // Log do erro
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o livro: " + e.getMessage());
        }




    }













}
