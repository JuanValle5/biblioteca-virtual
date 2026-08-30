package com.juanjo.biblioteca.service;


import com.juanjo.biblioteca.dto.book.BookRequestDTO;
import com.juanjo.biblioteca.exception.AuthorNotFoundException;
import com.juanjo.biblioteca.exception.BookNotFoundException;
import com.juanjo.biblioteca.model.Author;
import com.juanjo.biblioteca.model.Book;
import com.juanjo.biblioteca.repository.AuthorRepository;
import com.juanjo.biblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book create (BookRequestDTO dto){
        Author author = null;

        if (dto.authorId() != null) {
            author = authorRepository.findById(dto.authorId())
                    .orElseThrow(() -> new AuthorNotFoundException(dto.authorId()));
        }

        Book book = Book.builder()
                .title(dto.title())
                .isbn(dto.isbn())
                .description(dto.description())
                .publicationDate(dto.publicationDate())
                .pages(dto.pages())
                .language(dto.language())
                .genre(dto.genre())
                .publisher(dto.publisher())
                .author(author)
                .read(dto.read())
                .build();

        return bookRepository.save(book);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public void delete(Long id){
        if (!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
