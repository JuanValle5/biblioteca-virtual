package com.juanjo.biblioteca.dto.author.response;

public record AuthorCreateResponseDTO(
        Long id,
        String name,
        String nationality
) {
}
