package com.juanjo.biblioteca.dto.author.response;

import java.util.List;

public record AuthorResponseDTO(
        Long id,
        String name,
        String nationality,
        Integer bookCount,
        List<String> bookTitles
)
{ }
