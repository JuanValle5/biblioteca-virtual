package com.juanjo.biblioteca.service;


import com.juanjo.biblioteca.dto.author.request.AuthorRequestDTO;
import com.juanjo.biblioteca.dto.author.response.AuthorResponseDTO;
import com.juanjo.biblioteca.exception.AuthorHasBooksException;
import com.juanjo.biblioteca.exception.AuthorNotFoundException;
import com.juanjo.biblioteca.model.Author;
import com.juanjo.biblioteca.model.Book;
import com.juanjo.biblioteca.repository.AuthorRepository;
import com.juanjo.biblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author create(AuthorRequestDTO dto){
        Author author = Author.builder()
                .name(dto.name())
                .nationality(dto.nationality())
                .build();
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public List<AuthorResponseDTO> findAll(){
        return authorRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public AuthorResponseDTO findById(Long id){
        return toResponseDTO(findAuthorEntityById(id));
    }



    public void delete(Long id){
        //Aca no llamo a excepcion porque ya la tiene el metodo findbyid
        Author author = findAuthorEntityById(id);
        if (bookRepository.existsByAuthor(author)){
            throw new AuthorHasBooksException(id);
        }
        authorRepository.deleteById(id);
    }

    private AuthorResponseDTO toResponseDTO(Author author){

        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getNationality(),
                author.getBooks().size(),
                author.getBooks().stream().map(Book::getTitle).toList()
        );
    }

    private Author findAuthorEntityById(Long id){
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
