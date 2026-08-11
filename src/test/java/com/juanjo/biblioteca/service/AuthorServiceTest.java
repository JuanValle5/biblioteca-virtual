package com.juanjo.biblioteca.service;

import com.juanjo.biblioteca.dto.author.response.AuthorResponseDTO;
import com.juanjo.biblioteca.model.Author;
import com.juanjo.biblioteca.repository.AuthorRepository;
import com.juanjo.biblioteca.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void findById_deberiaRetornarAutor_cuandoExiste() {
        // Arrange
        Author author = Author.builder()
                .id(1L)
                .name("Gabriel García Márquez")
                .nationality("Colombiana")
                .books(List.of())
                .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        // Act
        AuthorResponseDTO result = authorService.findById(1L);

        // Assert
        assertEquals("Gabriel García Márquez", result.name());
        assertEquals("Colombiana", result.nationality());
        assertEquals(0, result.bookCount());
    }
}