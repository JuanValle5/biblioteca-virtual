package com.juanjo.biblioteca.controller;


import com.juanjo.biblioteca.dto.book.BookRequestDTO;
import com.juanjo.biblioteca.dto.book.BookResponseDTO;
import com.juanjo.biblioteca.model.Book;
import com.juanjo.biblioteca.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@Valid @RequestBody BookRequestDTO dto){
        Book book = bookService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(book));
    }

    @GetMapping
    public List<BookResponseDTO> findAll(){
        return bookService.findAll().stream().map(this::toResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(toResponseDTO(bookService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }


    private BookResponseDTO toResponseDTO(Book book){

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getPages(),
                book.getRead(),
                (book.getAuthor() == null) ? null : book.getAuthor().getName()
        );


    }
}
