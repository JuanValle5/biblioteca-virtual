package com.juanjo.biblioteca.repository;

import com.juanjo.biblioteca.model.Author;
import com.juanjo.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    boolean existsByAuthor(Author author);
}
