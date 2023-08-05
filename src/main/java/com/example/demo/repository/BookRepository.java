package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Book;
import java.util.List;
import java.util.Optional;


public interface BookRepository extends CrudRepository<Book, Long> {
    
    List<Book> findByTitle(String title);

    List<Book> findByAuthors_Name(String name);
    
    // @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :name")
    // List<Book> findBooksByAuthorName(@Param("name") String name);
}
