package com.juanjo.biblioteca.service;

import com.juanjo.biblioteca.dto.author.response.AuthorResponseDTO;
import com.juanjo.biblioteca.exception.AuthorHasBooksException;
import com.juanjo.biblioteca.model.Author;
import com.juanjo.biblioteca.repository.AuthorRepository;
import com.juanjo.biblioteca.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
                .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        // Act
        AuthorResponseDTO result = authorService.findById(1L);

        // Assert
        assertEquals("Gabriel García Márquez", result.name());
        assertEquals("Colombiana", result.nationality());
        assertEquals(0, result.bookCount());
    }

    @Test
    void delete_deberiaEliminar_cuandoAutorExisteYNoTieneLibros(){

        // Arrange
        Author author = Author.builder()
                .id(1L)
                .name("Gabriel García Márquez")
                .nationality("Colombiana")
                .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.existsByAuthor(author)).thenReturn(false);

        // Act
        authorService.delete(1L);

        // Assert
        verify(authorRepository).deleteById(1L);
    }

    @Test
    void delete_deberiaLanzarExcepcion_cuandoAutorTieneLibros() {
        // Arrange
        Author author = Author.builder()
                .id(1L)
                .name("Gabriel García Márquez")
                .nationality("Colombiana")
                .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.existsByAuthor(author)).thenReturn(true);

        // Act + Assert
        assertThrows(AuthorHasBooksException.class, () -> authorService.delete(1L));

        verify(authorRepository, never()).deleteById(any());
    }
}