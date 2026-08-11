package com.juanjo.biblioteca.dto.book;

public record BookResponseDTO (
        Long id,
        String title,
        Integer pages,
        Boolean read,
        String authorName
){}