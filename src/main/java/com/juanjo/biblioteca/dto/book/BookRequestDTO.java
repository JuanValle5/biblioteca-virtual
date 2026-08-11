package com.juanjo.biblioteca.dto.book;

public record BookRequestDTO (
        String title,
        Integer pages,
        Boolean read,
        Long authorId
){}
