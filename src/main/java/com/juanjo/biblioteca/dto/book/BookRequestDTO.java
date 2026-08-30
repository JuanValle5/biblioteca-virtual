package com.juanjo.biblioteca.dto.book;

import com.juanjo.biblioteca.model.Genre;

import java.time.LocalDate;

public record BookRequestDTO (
        String title,
        String isbn,
        String description,
        LocalDate publicationDate,
        Integer pages,
        String language,
        Genre genre,
        String publisher,
        Boolean read,
        Long authorId
){}
