package com.juanjo.biblioteca.controller;


import com.juanjo.biblioteca.dto.author.request.AuthorRequestDTO;
import com.juanjo.biblioteca.dto.author.response.AuthorCreateResponseDTO;
import com.juanjo.biblioteca.dto.author.response.AuthorResponseDTO;
import com.juanjo.biblioteca.model.Author;
import com.juanjo.biblioteca.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorCreateResponseDTO> create(@Valid @RequestBody AuthorRequestDTO dto){
        Author author = authorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseCreateDTO(author));
    }

    @GetMapping
    public List<AuthorResponseDTO> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }



    private AuthorCreateResponseDTO toResponseCreateDTO(Author author){

        return new AuthorCreateResponseDTO(
                author.getId(),
                author.getName(),
                author.getNationality()
        );

    }
}

