package com.bookstore.jpa.repositories;

import com.bookstore.jpa.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository <BookModel, UUID>{

BookModel findBookModelByTitle(String title);

@Query(value = "SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true)
List<BookModel> findBooksPublisherId(@Param("id") UUID id);


}
